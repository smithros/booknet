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

import com.kpi.booknet.booknet.model.Book;
import com.kpi.booknet.booknet.model.BookFile;
import com.kpi.booknet.booknet.model.BookPhoto;
import com.kpi.booknet.booknet.repos.BookFileRepository;
import com.kpi.booknet.booknet.repos.BookPhotoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookFilesService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    private final BookFileRepository fileRepo;
    private final BookPhotoRepository photoRepo;

    public BookPhoto getBookPhoto(final Book book) {
        return this.photoRepo.findByBookId(book.getId());
    }

    public BookPhoto deleteImage(final BookPhoto bookPhoto) {
        this.photoRepo.deleteByBookId(bookPhoto.getBookId());
        return bookPhoto;
    }

    public BookPhoto addImage(final BookPhoto bookPhoto) {
        this.photoRepo.addFile(bookPhoto.getBookId(), bookPhoto.getPhoto());
        return bookPhoto;
    }

    public BookPhoto updateImage(final BookPhoto bookPhoto) {
        this.photoRepo.updateFile(bookPhoto.getBookId(), bookPhoto.getPhoto());
        return bookPhoto;
    }

    public BookFile getBookFile(final Book book) {
        return this.fileRepo.findByBookId(book.getId());
    }

    public BookFile deleteFile(final BookFile bookFile) {
        this.fileRepo.deleteByBookId(bookFile.getBookId());
        return bookFile;
    }

    public BookFile addFile(final BookFile bookFile) {
        this.fileRepo.addFile(bookFile.getBookId(), bookFile.getFile());
        return bookFile;
    }

    public BookFile updateFile(final BookFile bookFile) {
        this.fileRepo.updateFile(bookFile.getBookId(), bookFile.getFile());
        return bookFile;
    }
}
