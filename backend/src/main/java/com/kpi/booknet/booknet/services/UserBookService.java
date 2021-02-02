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
import java.util.Optional;
import java.util.stream.Collectors;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.UserBook;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.UserBookRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService {
    private final BookRepository bookRepo;
    private final UserBookRepository userBookRepo;
    private final UserRepository userRepo;

    @Autowired
    public UserBookService(final BookRepository bookRepo,
                           final UserBookRepository userBookRepo,
                           final UserRepository userRepository
    ) {
        this.bookRepo = bookRepo;
        this.userBookRepo = userBookRepo;
        this.userRepo = userRepository;
    }

    public UserBook addBookToUser(final UserBook userBook) {
        return this.userBookRepo.save(userBook);
    }

    public List<Book> getAllUsersBooks(final long userId) {
        return this.userBookRepo.findAllBookIdsByUserId(userId)
            .stream()
            .map(this.bookRepo::findById)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    public List<Book> getAllFavouriteBooks(final long userId) {
        return this.userBookRepo.findAllByFavouriteIsTrueAndUserId(userId)
            .stream()
            .map(this.bookRepo::findById)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    public List<Book> getAllReadBooks(long userId) {
        return this.userBookRepo.findAllByReadIsTrueAndUserId(userId)
            .stream()
            .map(this.bookRepo::findById)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    public List<UserBook> getAllUserBooksByUserId(final long userId) {
        return this.userBookRepo.findAllByUserId(this.userRepo.findById(userId));
    }

    public UserBook deleteFromAdded(final UserBook ub) {
        this.userBookRepo.deleteByUserIdAndBookId(ub.getUserId(), ub.getBookId());
        return ub;
    }

    public UserBook markBookAsRead(final UserBook ub) {
        this.userBookRepo.markBookAsRead(ub.getUserId(), ub.getBookId());
        return ub;
    }

    public UserBook markBookAsFavourite(final UserBook ub) {
        this.userBookRepo.markBookAsFavourite(ub.getUserId(), ub.getBookId());
        return ub;
    }

    public UserBook removeFromRead(final UserBook ub) {
        this.userBookRepo.removeFromRead(ub.getUserId(), ub.getBookId());
        return ub;
    }

    public UserBook removeFromFavourite(final UserBook ub) {
        this.userBookRepo.removeFromFavourite(ub.getUserId(), ub.getBookId());
        return ub;
    }

    public UserBook getUserBookByBookUserId(UserBook userBook) {
        return this.userBookRepo.findByUserIdAndBookId(userBook.getUserId(), userBook.getBookId());
    }

}
