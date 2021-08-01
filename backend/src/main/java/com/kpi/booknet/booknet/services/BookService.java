/*
 * MIT License
 *
 * Copyright (c) 2020-2021 Rostyslav Koval
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.kpi.booknet.booknet.services;

import java.util.ArrayList;
import java.util.List;
import com.kpi.booknet.booknet.dto.BookDto;
import com.kpi.booknet.booknet.dto.BookFilter;
import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.Author;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.Genre;
import com.kpi.booknet.booknet.repos.AuthorRepository;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.GenreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository books;

    private final GenreRepository genres;

    private final AuthorRepository authors;

    private final ModelMapper mapper;

    public BookDto createBook(final BookDto book) {
        final Book entity = this.convertBookToEntity(book);
        if (this.getBookById(entity.getId()) == null) {
            this.books.save(entity);
            log.info("Saved book: {}", entity);
        }
        return book;
    }

    public Book getBookById(final long id) {
        log.info("Getting book with id: {}", id);
        return this.books.findById(id);
    }

    public Book updateBook(final Book book) {
        if (this.getBookById(book.getId()) != null) {
            this.books.updateBookById(
                book.getTitle(), book.getText(), book.getPhotoId(),
                book.getFileId(), book.isStatus(), book.getId());
            log.info("Updated book: {}", book);
            return book;
        } else {
            throw new BookNetException(ErrorType.NO_BOOK_WITH_SUCH_ID.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        return (List<Book>) this.books.findAll();
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) this.authors.findAll();
    }

    public List<Author> getAuthorsByBookId(final long id) {
        return this.authors.findByBookId(id);
    }

    public List<Genre> getAllGenres() {
        return this.genres.findAll();
    }

    public List<Genre> getGenreByBookId(final long id) {
        return this.genres.findByBookId(id);
    }

    public List<String> getAllGenresName() {
        return this.genres.findAllGenresNames();
    }

    private BookDto convertBookToDto(final Book book) {
        final BookDto dto = this.mapper.map(book, BookDto.class);
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setText(book.getText());
        dto.setStatus(book.isStatus());
        dto.setPhotoId(book.getPhotoId());
        dto.setFileId(book.getFileId());
        dto.setAuthors(book.getAuthors());
        dto.setGenres(book.getGenres());
        return dto;
    }

    private Book convertBookToEntity(final BookDto book) {
        final Book ent = this.mapper.map(book, Book.class);
        ent.setId(book.getId());
        ent.setTitle(book.getTitle());
        ent.setText(book.getText());
        ent.setStatus(book.isStatus());
        ent.setPhotoId(book.getPhotoId());
        ent.setFileId(book.getFileId());
        ent.setAuthors(book.getAuthors());
        ent.setGenres(book.getGenres());
        return ent;
    }

    //TODO dynamic query generation
    public List<Book> filterBooks(final BookFilter filter) {
        log.info("Got book filter: {}", filter);
        List<Book> res = new ArrayList<>();
        final String header = filter.getHeader();
        final List<String> genres = filter.getGenres();
        final List<String> authors = filter.getAuthors();
        final boolean bHeader = header == null || header.isEmpty();
        final boolean bGenre = genres == null || genres.isEmpty();
        final boolean bAuthor = authors == null || authors.isEmpty();

        if (!bHeader && !bGenre && !bAuthor) {
            res = this.books.filterBooks(header, genres, authors);
        }
        if (!bHeader && bGenre && bAuthor) {
            res = this.books.filterBooksByHeader(header);
        }
        if (!bGenre && bAuthor && bHeader) {
            res = this.books.filterBooksByGenres(genres);
        }
        if (!bAuthor && bGenre && bHeader) {
            res = this.books.filterBooksByAuthors(authors);
        }
        if (!bGenre && !bHeader && bAuthor) {
            res = this.books.filterBooksByHeaderAndGenres(header, genres);
        }
        if (!bAuthor && !bHeader && bGenre) {
            res = this.books.filterBooksByHeaderAndAuthors(header, authors);
        }
        if (!bGenre && !bAuthor && bHeader) {
            res = this.books.filterBooksByGenreAndAuthor(genres, authors);
        }
        return res;
    }
}
