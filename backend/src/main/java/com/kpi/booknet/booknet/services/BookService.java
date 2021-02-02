/*
 * MIT License
 *
 * Copyright (c) 2021 Koval Rostyslav
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
 *
 */

package com.kpi.booknet.booknet.services;

import java.util.List;
import com.kpi.booknet.booknet.model.Author;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.Genre;
import com.kpi.booknet.booknet.repos.AuthorRepository;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.GenreRepository;
import com.kpi.booknet.booknet.repos.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepo;
    private final GenreRepository genreRepo;
    private final AuthorRepository authorRepo;
    private final UserBookRepository userBookRepo;

    @Autowired
    public BookService(final BookRepository bookRepo,
                       final GenreRepository genreRepo,
                       final AuthorRepository authorRepo,
                       final UserBookRepository userBookRepo
    ) {
        this.bookRepo = bookRepo;
        this.genreRepo = genreRepo;
        this.authorRepo = authorRepo;
        this.userBookRepo = userBookRepo;
    }

    public Book createBook(final Book book) {
        if (this.bookRepo.findById(book.getId()) == null) {
            this.bookRepo.save(book);
        }
        return book;
    }

    public Book getBookById(final long bookId) {
        return this.bookRepo.findById(bookId);
    }

    public Book updateBook(final Book book) {
        if (this.bookRepo.findById(book.getId()) != null) {
            this.bookRepo.updateBookById(
                book.getTitle(), book.getText(), book.getPhotoId().getId(),
                book.getFileId().getId(), book.isStatus(), book.getId());
            return book;
        } else {
            throw new IllegalStateException("There is no book with such a id");
        }
    }

    public List<Book> getAllBooks() {
        return (List<Book>) this.bookRepo.findAll();
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) this.authorRepo.findAll();
    }

    public List<Author> getAuthorsByBookId(long bookId) {
        return this.authorRepo.findAuthorsByBookId(bookId);
    }

    public List<Genre> getAllGenres() {
        return this.genreRepo.findAll();
    }

    public Genre getGenreByBookId(long bookId) {
        return this.genreRepo.findById(bookId);
    }

    public List<String> getAllGenresName() {
        return this.genreRepo.findAllGenresNames();
    }

}