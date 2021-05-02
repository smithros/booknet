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
import java.util.Optional;
import java.util.stream.Collectors;
import com.kpi.booknet.booknet.dto.UserBookDto;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.UserBook;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.UserBookRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBookService {
    private final BookRepository bookRepo;
    private final UserBookRepository userBookRepo;
    private final UserRepository userRepo;
    private final ModelMapper mapper;

    public UserBookDto addBookToUser(final UserBookDto userBook) {
        final UserBook ent = this.convertUbToEntity(userBook);
        if (this.userAlreadyHasBook(userBook, ent)) {
            this.userBookRepo.save(ent);
        }
        return userBook;
    }

    public List<Book> getAllUsersBooks(final long userId) {
        return this.constructListOfBooks(this.userBookRepo.findAllBookIdsByUserId(userId));
    }

    public List<Book> getAllFavouriteBooks(final long userId) {
        return this.constructListOfBooks(
            this.userBookRepo.findAllByFavouriteIsTrueAndUserId(userId));
    }

    public List<Book> getAllReadBooks(long userId) {
        return this.constructListOfBooks(
            this.userBookRepo.findAllByReadIsTrueAndUserId(userId));
    }

    public List<UserBook> getAllUserBooksByUserId(final long userId) {
        return this.userBookRepo.findAllByUserId(this.userRepo.findById(userId));
    }

    public UserBookDto deleteFromAdded(final UserBookDto ub) {
        final UserBook ent = this.convertUbToEntity(ub);
        this.userBookRepo.deleteByUserIdAndBookId(ent.getUserId(), ent.getBookId());
        return ub;
    }

    public UserBookDto markBookAsRead(final UserBookDto ub) {
        final UserBook ent = this.convertUbToEntity(ub);
        this.userBookRepo.markBookAsRead(ent.getUserId(), ent.getBookId());
        return ub;
    }

    public UserBookDto markBookAsFavourite(final UserBookDto ub) {
        final UserBook ent = this.convertUbToEntity(ub);
        this.userBookRepo.markBookAsFavourite(ent.getUserId(), ent.getBookId());
        return ub;
    }

    public UserBookDto removeFromRead(final UserBookDto ub) {
        final UserBook ent = this.convertUbToEntity(ub);
        this.userBookRepo.removeFromRead(ent.getUserId(), ent.getBookId());
        return ub;
    }

    public UserBookDto removeFromFavourite(final UserBookDto ub) {
        final UserBook ent = this.convertUbToEntity(ub);
        this.userBookRepo.removeFromFavourite(ent.getUserId(), ent.getBookId());
        return ub;
    }

    public UserBookDto getUserBookByBookUserId(final UserBookDto ub) {
        final UserBook ent = this.convertUbToEntity(ub);
        return this.convertUbToDto(
            this.userBookRepo.findByUserIdAndBookId(ent.getUserId(), ent.getBookId()));
    }

    private List<Book> constructListOfBooks(final List<Long> ids) {
        return ids.stream()
            .map(this.bookRepo::findById)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    private boolean userAlreadyHasBook(final UserBookDto userBook, final UserBook ent) {
        return this.getAllUsersBooks(userBook.getUserId())
            .stream()
            .filter(book -> book.getId() == ent.getBookId().getId())
            .findFirst()
            .orElse(null) == null;
    }

    private UserBookDto convertUbToDto(final UserBook ub) {
        UserBookDto dto = this.mapper.map(ub, UserBookDto.class);
        dto.setId(ub.getId());
        dto.setBookId(ub.getBookId().getId());
        dto.setUserId(ub.getUserId().getId());
        dto.setFavourite(ub.isFavourite());
        dto.setRead(ub.isRead());
        return dto;
    }

    private UserBook convertUbToEntity(final UserBookDto ub) {
        UserBook ent = this.mapper.map(ub, UserBook.class);
        ent.setId(ub.getId());
        ent.setBookId(this.bookRepo.findById(ub.getBookId()).get());
        ent.setUserId(this.userRepo.findById(ub.getUserId()).get());
        ent.setFavourite(ub.isFavourite());
        ent.setRead(ub.isRead());
        return ent;
    }
}
