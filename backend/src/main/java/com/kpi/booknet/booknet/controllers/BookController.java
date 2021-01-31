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

package com.kpi.booknet.booknet.controllers;

import java.util.List;
import java.util.Optional;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody final Book book) {
        final Book response = bookService.createBook(book);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Book> updateBook(@RequestBody final Book book) {
        final Book response = bookService.updateBook(book);
        System.out.println(book);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") final long bookId) {
        final Book response = bookService.getBookById(bookId);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenres() {
        return ResponseEntity.ok(bookService.getAllGenres());
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAuthors() {
        return ResponseEntity.ok(bookService.getAllAuthors());
    }

    @RequestMapping(value = "/genresName", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenresName() {
        return ResponseEntity.ok(bookService.getAllGenresName());
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorsByBookId(@PathVariable(name = "id") final long bookId) {
        return ResponseEntity.ok(bookService.getAuthorsByBookId(bookId));
    }

    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGenreByBookId(@PathVariable(name = "id") final long bookId) {
        return ResponseEntity.ok(bookService.getGenreByBookId(bookId));
    }
}
