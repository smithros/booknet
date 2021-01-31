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

package com.kpi.booknet.booknet.repos;

import java.util.List;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.User;
import com.kpi.booknet.booknet.model.UserBook;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserBookRepository extends CrudRepository<UserBook, Long> {

    @Query(value = "select book_id from usr_book where user_id = ?1", nativeQuery = true)
    List<Long> findAllBookIdsByUserId(long id);

    List<UserBook> findAll();

    List<UserBook> findAllByUserId(User user);

    UserBook findByUserIdAndBookId(User user, Book book);

    @Query(
        value = "select book_id from usr_book where is_favourite = true and user_id = ?1",
        nativeQuery = true
    )
    List<Long> findAllByFavouriteIsTrueAndUserId(long id);

    @Query(
        value = "select book_id from usr_book where is_read = true and user_id = ?1",
        nativeQuery = true
    )
    List<Long> findAllByReadIsTrueAndUserId(long id);

    @Transactional
    @Modifying
    @Query("update UserBook ub set ub.read = true where ub.userId = :userId " +
        "and ub.bookId = :bookId")
    void markBookAsRead(@Param("userId") long userId, @Param("bookId") long bookId);

    @Transactional
    @Modifying
    @Query("update UserBook ub set ub.favourite = true where ub.userId = :userId " +
        "and ub.bookId = :bookId")
    void markBookAsFavourite(@Param("userId") long userId, @Param("bookId") long bookId);

    @Transactional
    @Modifying
    @Query("update UserBook ub set ub.read = false where ub.userId = :userId " +
        "and ub.bookId = :bookId")
    void removeFromRead(@Param("userId") long userId, @Param("bookId") long bookId);

    @Transactional
    @Modifying
    @Query("update UserBook ub set ub.favourite = false where ub.userId = :userId " +
        "and ub.bookId = :bookId")
    void removeFromFavourite(@Param("userId") long userId, @Param("bookId") long bookId);

    void deleteByUserIdAndBookId(User userId, Book bookId);
}
