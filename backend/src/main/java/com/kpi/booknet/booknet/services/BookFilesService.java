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

    private final BookFileRepository files;

    private final BookPhotoRepository photos;

    public BookPhoto getBookPhoto(final Book book) {
        return this.photos.findByBookId(book.getId());
    }

    public BookPhoto deleteImage(final BookPhoto photo) {
        this.photos.deleteByBookId(photo.getBookId());
        return photo;
    }

    public BookPhoto addImage(final BookPhoto photo) {
        this.photos.addFile(photo.getBookId(), photo.getPhoto());
        return photo;
    }

    public BookPhoto updateImage(final BookPhoto photo) {
        this.photos.updateFile(photo.getBookId(), photo.getPhoto());
        return photo;
    }

    public BookFile getBookFile(final Book book) {
        return this.files.findByBookId(book.getId());
    }

    public BookFile deleteFile(final BookFile file) {
        this.files.deleteByBookId(file.getBookId());
        return file;
    }

    public BookFile addFile(final BookFile file) {
        this.files.addFile(file.getBookId(), file.getFile());
        return file;
    }

    public BookFile updateFile(final BookFile file) {
        this.files.updateFile(file.getBookId(), file.getFile());
        return file;
    }
}
