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

package com.kpi.booknet.booknet.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.BookFile;
import com.kpi.booknet.booknet.model.BookPhoto;
import com.kpi.booknet.booknet.services.BookFilesService;
import com.kpi.booknet.booknet.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/book")
@AllArgsConstructor
public final class BookController {
    private final BookService service;
    private final BookFilesService files;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody final Book book) {
        final Book response = this.service.createBook(book);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Book> updateBook(@RequestBody final Book book) {
        final Book response = this.service.updateBook(book);
        System.out.println(book);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") final long bookId) {
        final Book response = this.service.getBookById(bookId);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return this.service.getAllBooks();
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenres() {
        return ResponseEntity.ok(this.service.getAllGenres());
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAuthors() {
        return ResponseEntity.ok(this.service.getAllAuthors());
    }

    @RequestMapping(value = "/genresName", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenresName() {
        return ResponseEntity.ok(this.service.getAllGenresName());
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorsByBookId(@PathVariable(name = "id") final long bookId) {
        return ResponseEntity.ok(this.service.getAuthorsByBookId(bookId));
    }

    @RequestMapping(value = "/genres/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGenresByBookId(@PathVariable(name = "id") final long bookId) {
        return ResponseEntity.ok(this.service.getGenreByBookId(bookId));
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public ResponseEntity<BookFile> addBookFile(@RequestParam(name = "bookId") final long bookId,
                                                @RequestParam(name = "file") final MultipartFile file) throws IOException {
        BookFile bookFile = new BookFile(bookId, file.getBytes());
        BookFile response = this.files.addFile(bookFile);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.POST)
    public ResponseEntity<BookPhoto> addBookImage(@RequestParam(name = "bookId") final long bookId,
                                                  @RequestParam(name = "img") final MultipartFile file) throws IOException {
        BookPhoto bookImage = new BookPhoto(bookId, file.getBytes());
        BookPhoto response = this.files.addImage(bookImage);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, value = "/bookFile", method = RequestMethod.POST)
    public ResponseEntity<?> getBookFile(@RequestBody final Book book) {
        if (this.files.getBookFile(book) == null) {
            return ResponseEntity.ok(null);
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(files.getBookFile(book).getFile());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(stream));
    }

    @RequestMapping(produces = MediaType.IMAGE_PNG_VALUE, value = "/bookImage")
    @ResponseBody
    public ResponseEntity<byte[]> getBookImage(@RequestBody final Book book) {
        BookPhoto bookImage = this.files.getBookPhoto(book);
        return Optional.ofNullable(bookImage.getPhoto())
            .map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/updateFile", method = RequestMethod.POST)
    public ResponseEntity<BookFile> updateBookFile(@RequestBody final Book book,
                                                   @RequestParam(name = "file") final MultipartFile file) throws IOException {
        BookFile bookFile = this.files.getBookFile(book);
        bookFile.setFile(file.getBytes());
        BookFile response = this.files.updateFile(bookFile);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public ResponseEntity<BookPhoto> updateBookImage(@RequestBody final Book book,
                                                     @RequestParam(name = "img") final MultipartFile file) throws IOException {
        BookPhoto bookImage = this.files.getBookPhoto(book);
        bookImage.setPhoto(file.getBytes());
        BookPhoto response = this.files.updateImage(bookImage);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    public ResponseEntity<BookPhoto> deleteBookImage(@RequestBody final Book book) {
        BookPhoto bookImage = this.files.getBookPhoto(book);
        BookPhoto response = this.files.deleteImage(bookImage);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<BookFile> deleteBookFile(@RequestBody final Book book) {
        BookFile bookFile = this.files.getBookFile(book);
        BookFile response = this.files.deleteFile(bookFile);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
