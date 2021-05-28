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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBookService {

    private static final Logger LOG = LoggerFactory.getLogger(UserBookService.class);

    private final BookRepository books;

    private final UserBookRepository usrbooks;

    private final UserRepository users;

    private final ModelMapper mapper;

    public UserBookDto addBookToUser(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        if (this.userAlreadyHasBook(dto, ent)) {
            LOG.info("Added book id: {} to user id: {}", ent.getBookId(), ent.getUserId());
            this.usrbooks.save(ent);
        }
        return dto;
    }

    public List<Book> getAllUsersBooks(final long uid) {
        return this.constructListOfBooks(this.usrbooks.findAllBookIdsByUserId(uid));
    }

    public List<Book> getAllFavouriteBooks(final long uid) {
        return this.constructListOfBooks(
            this.usrbooks.findAllByFavouriteIsTrueAndUserId(uid));
    }

    public List<Book> getAllReadBooks(final long uid) {
        return this.constructListOfBooks(this.usrbooks.findAllByReadIsTrueAndUserId(uid));
    }

    public List<UserBook> getAllUserBooksByUserId(final long uid) {
        return this.usrbooks.findAllByUserId(this.users.findById(uid));
    }

    public UserBookDto deleteFromAdded(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        this.usrbooks.deleteByUserIdAndBookId(ent.getUserId(), ent.getBookId());
        LOG.info("Deleted book id: {} from user id: {}", ent.getBookId(), ent.getUserId());
        return dto;
    }

    public UserBookDto markBookAsRead(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        this.usrbooks.markBookAsRead(ent.getUserId(), ent.getBookId());
        LOG.info("Mark as read book id: {} and user id: {}", ent.getBookId(), ent.getUserId());
        return dto;
    }

    public UserBookDto markBookAsFavourite(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        this.usrbooks.markBookAsFavourite(ent.getUserId(), ent.getBookId());
        LOG.info("Mark as favourite book id: {} and user id: {}", ent.getBookId(), ent.getUserId());
        return dto;
    }

    public UserBookDto removeFromRead(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        this.usrbooks.removeFromRead(ent.getUserId(), ent.getBookId());
        LOG.info("Remove from read book id: {} and user id: {}", ent.getBookId(), ent.getUserId());
        return dto;
    }

    public UserBookDto removeFromFavourite(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        this.usrbooks.removeFromFavourite(ent.getUserId(), ent.getBookId());
        LOG.info("Remove from fav book id: {} and user id: {}", ent.getBookId(), ent.getUserId());
        return dto;
    }

    public UserBookDto getUserBookByBookUserId(final UserBookDto dto) {
        final UserBook ent = this.convertUbToEntity(dto);
        return this.convertUbToDto(
            this.usrbooks.findByUserIdAndBookId(ent.getUserId(), ent.getBookId()));
    }

    private List<Book> constructListOfBooks(final List<Long> ids) {
        return ids.stream()
            .map(this.books::findById)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    private boolean userAlreadyHasBook(final UserBookDto dto, final UserBook ent) {
        return this.getAllUsersBooks(dto.getUserId())
            .stream()
            .filter(book -> book.getId() == ent.getBookId().getId())
            .findFirst()
            .orElse(null) == null;
    }

    private UserBookDto convertUbToDto(final UserBook ent) {
        final UserBookDto dto = this.mapper.map(ent, UserBookDto.class);
        dto.setId(dto.getId());
        dto.setBookId(ent.getBookId().getId());
        dto.setUserId(ent.getUserId().getId());
        dto.setFavourite(dto.isFavourite());
        dto.setRead(dto.isRead());
        return dto;
    }

    private UserBook convertUbToEntity(final UserBookDto dto) {
        final UserBook ent = this.mapper.map(dto, UserBook.class);
        ent.setId(dto.getId());
        ent.setBookId(this.books.findById(dto.getBookId()).get());
        ent.setUserId(this.users.findById(dto.getUserId()).get());
        ent.setFavourite(dto.isFavourite());
        ent.setRead(dto.isRead());
        return ent;
    }
}
