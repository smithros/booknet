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
import com.kpi.booknet.booknet.model.UserBook;
import com.kpi.booknet.booknet.services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserBookController {
    private final UserBookService userBookService;

    @Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserBook> addBookToUser(@RequestBody UserBook userBook) {
        final UserBook response = userBookService.addBookToUser(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<UserBook> getUserBookById(@RequestBody UserBook userBook) {
        final UserBook response = userBookService.getUserBookByBookUserId(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAllUsersBooks(@RequestParam(name = "userId") long userId) {
        return userBookService.getAllUsersBooks(userId);
    }

    @RequestMapping(value = "/all/favourite", method = RequestMethod.GET)
    public List<Book> getAllFavouriteBooks(@RequestParam(name = "userId") long userId) {
        return userBookService.getAllFavouriteBooks(userId);
    }

    @RequestMapping(value = "/all/read", method = RequestMethod.GET)
    public List<Book> getAllReadBooks(@RequestParam(name = "userId") long userId) {
        return userBookService.getAllReadBooks(userId);
    }

    @RequestMapping(value = "/mark_read", method = RequestMethod.POST)
    public ResponseEntity<UserBook> markBookAsRead(@RequestBody UserBook userBook) {
        UserBook response = userBookService.markBookAsRead(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/mark_fav", method = RequestMethod.POST)
    public ResponseEntity<UserBook> markBookAsFavourite(@RequestBody UserBook userBook) {
        UserBook response = userBookService.markBookAsFavourite(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/remove_read", method = RequestMethod.POST)
    public ResponseEntity<UserBook> removeFromRead(@RequestBody UserBook userBook) {
        UserBook response = userBookService.removeFromRead(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/remove_fav", method = RequestMethod.POST)
    public ResponseEntity<UserBook> removeFromFavourite(@RequestBody UserBook userBook) {
        UserBook response = userBookService.removeFromFavourite(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<UserBook> deleteBookFromAdded(@RequestBody UserBook userBook) {
        UserBook response = userBookService.deleteFromAdded(userBook);
        return Optional.ofNullable(response).map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/getAllById", method = RequestMethod.GET)
    public List<UserBook> getAllUserBooksByUserId(@RequestParam(name = "userId") long userId) {
        return userBookService.getAllUserBooksByUserId(userId);
    }
}


