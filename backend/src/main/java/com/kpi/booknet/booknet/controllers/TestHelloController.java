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

import java.util.Set;
import com.kpi.booknet.booknet.UserRole;
import com.kpi.booknet.booknet.model.Achievement;
import com.kpi.booknet.booknet.model.Author;
import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.BookFile;
import com.kpi.booknet.booknet.model.BookPhoto;
import com.kpi.booknet.booknet.model.Genre;
import com.kpi.booknet.booknet.model.RecoverCode;
import com.kpi.booknet.booknet.model.User;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestHelloController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestHelloController(final BookRepository bookRepository, final UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/book/save")
    public String testBookSave() {
        BookPhoto bookPhoto = new BookPhoto();
        bookPhoto.setName("BookPhoto1");
        BookFile bookFile = new BookFile();
        bookFile.setName("BookFile1");

        Author author = new Author();
        author.setName("Newells");
        Author author2 = new Author();
        author2.setName("Herbelts");
        Genre genre = new Genre();
        genre.setDesc("Lyrics");
        Genre genre1 = new Genre();
        genre1.setDesc("fantasy");

        Book book = new Book();
        book.setTitle("test_book");
        book.setIntroText("someText");
        book.setStatus(true);
        book.setPhotoId(bookPhoto);
        book.setFileId(bookFile);
        book.setAuthors(Set.of(author, author2));
        book.setGenres(Set.of(genre, genre1));

        bookRepository.save(book);
        return "passed";
    }

    @GetMapping("/test/save")
    public String testUserSave() {
        User user = new User();
        user.setName("user1");
        user.setPassword("user1");
        user.setEmail("example@com.ua");
        user.setActivated(true);
        user.setVerified(true);
        user.setRole(UserRole.USER);
        RecoverCode recoverCode = new RecoverCode();
        recoverCode.setCode("111");
        recoverCode.setEmail(user.getEmail());
        user.setRecoverCode(recoverCode);


        Genre genre = new Genre();
        genre.setDesc("GenreTest");

        Achievement achievement = new Achievement();
        achievement.setCount(10);
        achievement.setDescription("Achiv");
        achievement.setEntity("Entity");
        achievement.setGenreId(genre);

        user.setAchievements(Set.of(achievement));

        userRepository.save(user);
        return "passed";
    }


    @GetMapping("/book/get/{id}")
    public Book testGetId(@PathVariable(name = "id") Long id) {
        return bookRepository.findById(id).get();
    }

    @GetMapping("/test/get/{id}")
    public User testUserGet(@PathVariable(name = "id") Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/")
    public String method() {
        return "test app running";
    }
}
