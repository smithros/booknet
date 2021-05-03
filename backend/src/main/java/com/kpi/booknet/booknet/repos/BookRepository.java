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

package com.kpi.booknet.booknet.repos;

import java.util.List;
import com.kpi.booknet.booknet.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findById(long id);

    @Query(
        value = "select b.* from book_author ab, book b where ab.book_id = b.book_id and ab.author_id = ?1",
        nativeQuery = true
    )
    List<Book> findBooksByAuthorId(long authorId);

    @Transactional
    @Modifying
    @Query(value = "insert into book_author (book_id, author_id) values (?1, ?2)", nativeQuery = true)
    void connectAuthorAndBook(long bookId, long authorId);

    @Transactional
    @Modifying
    @Query(value = "update book set title = :title, text = :text, photo_id = :photoId, file_id = :fileId, status = :status where book_id = :bookId", nativeQuery = true)
    void updateBookById(String title, String text, long photoId, long fileId, boolean status, long bookId);

}
