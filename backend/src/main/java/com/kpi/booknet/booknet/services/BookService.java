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

import java.util.List;
import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.Author;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.Genre;
import com.kpi.booknet.booknet.repos.AuthorRepository;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.GenreRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepo;
    private final GenreRepository genreRepo;
    private final AuthorRepository authorRepo;

    public Book createBook(final Book book) {
        if (this.getBookById(book.getId()) == null) {
            this.bookRepo.save(book);
            LOG.info("Saved book: {}", book);
        }
        return book;
    }

    public Book getBookById(final long bookId) {
        return this.bookRepo.findById(bookId);
    }

    public Book updateBook(final Book book) {
        if (this.getBookById(book.getId()) != null) {
            this.bookRepo.updateBookById(
                book.getTitle(), book.getText(), book.getPhotoId().getId(),
                book.getFileId().getId(), book.isStatus(), book.getId());
            return book;
        } else {
            throw new BookNetException(ErrorType.NO_BOOK_WITH_SUCH_ID.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        return (List<Book>) this.bookRepo.findAll();
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) this.authorRepo.findAll();
    }

    public List<Author> getAuthorsByBookId(final long bookId) {
        return this.authorRepo.findByBookId(bookId);
    }

    public List<Genre> getAllGenres() {
        return this.genreRepo.findAll();
    }

    public List<Genre> getGenreByBookId(final long bookId) {
        return this.genreRepo.findByBookId(bookId);
    }

    public List<String> getAllGenresName() {
        return this.genreRepo.findAllGenresNames();
    }
}
