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

package com.kpi.booknet.booknet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookNetApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookNetApplication.class, args);
    }

	/*@Bean
	CommandLineRunner init(BookRepository bookRepository, UserRepository userRepository) {
		return args -> {
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

			Genre genre3 = new Genre();
			genre.setDesc("GenreTest");
			Achievement achievement = new Achievement();
			achievement.setCount(10);
			achievement.setDescription("Achiv");
			achievement.setEntity("Entity");
			achievement.setGenreId(genre3);
			user.setAchievements(Set.of(achievement));
			userRepository.save(user);
		};
	}*/
}
