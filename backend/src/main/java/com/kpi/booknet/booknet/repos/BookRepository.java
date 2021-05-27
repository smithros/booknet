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
        value = "select b.* from book_author ab, book b " +
            "where ab.book_id = b.book_id and ab.author_id = ?1",
        nativeQuery = true
    )
    List<Book> findBooksByAuthorId(long aid);

    @Transactional
    @Modifying
    @Query(value = "insert into book_author (book_id, author_id) values (?1, ?2)", nativeQuery = true)
    void connectAuthorAndBook(long bid, long aid);

    @Transactional
    @Modifying
    @Query(value = "update book set title = :title, text = :text, photo_id = :pid, " +
        "file_id = :fid, status = :status where book_id = :bid", nativeQuery = true)
    void updateBookById(String title, String text,
                        long pid, long fid,
                        boolean status, long bid);

    //TODO dynamic query generation
    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "    join book_author ba on b.book_id    = ba.book_id\n" +
            "    join book_genre  bg on b.book_id    = bg.book_id\n" +
            "    join genre       g  on bg.genre_id  = g.genre_id\n" +
            "    join author      a  on ba.author_id = a.author_id\n" +
            "\n" +
            "where\n" +
            "    upper(b.title) like upper('%'||?1||'%')\n" +
            "    and g.description in (?2)\n" +
            "    and a.name in (?3)\n",
        nativeQuery = true
    )
    List<Book> filterBooks(String header, List<String> genres, List<String> authors);

    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "where\n" +
            "    upper(b.title) like upper('%'||?1||'%')\n",
        nativeQuery = true
    )
    List<Book> filterBooksByHeader(String header);

    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "    join book_genre  bg on b.book_id    = bg.book_id\n" +
            "    join genre       g  on bg.genre_id  = g.genre_id\n" +
            "\n" +
            "where\n" +
            "    g.description in (?1)\n",
        nativeQuery = true
    )
    List<Book> filterBooksByGenres(List<String> genres);

    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "    join book_author ba on b.book_id    = ba.book_id\n" +
            "    join author      a  on ba.author_id = a.author_id\n" +
            "\n" +
            "where\n" +
            "    a.name in (?1)\n",
        nativeQuery = true
    )
    List<Book> filterBooksByAuthors(List<String> authors);

    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "    join book_genre  bg on b.book_id    = bg.book_id\n" +
            "    join genre       g  on bg.genre_id  = g.genre_id\n" +
            "\n" +
            "where\n" +
            "    upper(b.title) like upper('%'||?1||'%')\n" +
            "    and g.description in (?2)\n",
        nativeQuery = true
    )
    List<Book> filterBooksByHeaderAndGenres(String header, List<String> genre);

    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "    join book_author ba on b.book_id    = ba.book_id\n" +
            "    join author      a  on ba.author_id = a.author_id\n" +
            "\n" +
            "where\n" +
            "    upper(b.title) like upper('%'||?1||'%')\n" +
            "    and a.name in (?2)\n",
        nativeQuery = true
    )
    List<Book> filterBooksByHeaderAndAuthors(String header, List<String> authors);

    @Query(
        value = "select\n" +
            "    b.*\n" +
            "from\n" +
            "    book b\n" +
            "    join book_author ba on b.book_id    = ba.book_id\n" +
            "    join book_genre  bg on b.book_id    = bg.book_id\n" +
            "    join genre       g  on bg.genre_id  = g.genre_id\n" +
            "    join author      a  on ba.author_id = a.author_id\n" +
            "\n" +
            "where\n" +
            "    g.description in (?1)\n" +
            "    and a.name in (?2)\n",
        nativeQuery = true
    )
    List<Book> filterBooksByGenreAndAuthor(List<String> genres, List<String> authors);
}
