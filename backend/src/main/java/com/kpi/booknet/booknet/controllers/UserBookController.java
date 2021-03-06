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

import java.util.List;
import java.util.Optional;
import com.kpi.booknet.booknet.dto.UserBookDto;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.UserBook;
import com.kpi.booknet.booknet.services.UserBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/userBook")
@AllArgsConstructor
public final class UserBookController {

    private final UserBookService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserBookDto> addBookToUser(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.addBookToUser(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<UserBookDto> getUserBookById(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.getUserBookByBookUserId(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllUsersBooks(
        @RequestParam(name = "userId") final long id
    ) {
        final List<Book> response = this.service.getAllUsersBooks(id);
        return response.isEmpty()
            ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/all/favourite", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllFavouriteBooks(
        @RequestParam(name = "userId") final long id
    ) {
        final List<Book> response = this.service.getAllFavouriteBooks(id);
        return response.isEmpty()
            ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/all/read", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllReadBooks(
        @RequestParam(name = "userId") final long id
    ) {
        final List<Book> response = this.service.getAllReadBooks(id);
        return response.isEmpty()
            ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/mark_read", method = RequestMethod.POST)
    public ResponseEntity<UserBookDto> markBookAsRead(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.markBookAsRead(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/mark_fav", method = RequestMethod.POST)
    public ResponseEntity<UserBookDto> markBookAsFavourite(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.markBookAsFavourite(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/remove_read", method = RequestMethod.POST)
    public ResponseEntity<UserBookDto> removeFromRead(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.removeFromRead(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/remove_fav", method = RequestMethod.POST)
    public ResponseEntity<UserBookDto> removeFromFavourite(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.removeFromFavourite(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<UserBookDto> deleteBookFromAdded(@RequestBody final UserBookDto dto) {
        return Optional.ofNullable(this.service.deleteFromAdded(dto))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/getAllById", method = RequestMethod.GET)
    public ResponseEntity<List<UserBook>> getAllUserBooksByUserId(
        @RequestParam(name = "userId") final long id
    ) {
        final List<UserBook> response = this.service.getAllUserBooksByUserId(id);
        return response.isEmpty()
            ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
    }
}
