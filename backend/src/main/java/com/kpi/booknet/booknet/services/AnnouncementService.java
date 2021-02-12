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
import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.Announcement;
import com.kpi.booknet.booknet.repos.AnnouncementRepository;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    @Autowired
    public AnnouncementService(final AnnouncementRepository announcementRepo,
                               final BookRepository bookRepo,
                               final UserRepository userRepo
    ) {
        this.announcementRepo = announcementRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public Announcement getById(final long id) {
        return this.announcementRepo.findById(id);
    }

    public List<Announcement> getAll() {
        return (List<Announcement>) this.announcementRepo.findAll();
    }

    public List<Announcement> getPublished() {
        return this.announcementRepo.findAnnouncementsByStatusTrue();
    }

    public List<Announcement> getUnpublished() {
        return this.announcementRepo.findAnnouncementsByStatusFalse();
    }

    public Announcement delete(final long id) {
        if (this.getById(id) != null) {
            return this.announcementRepo.deleteById(id);
        }
        throw new BookNetException(ErrorType.NO_SUCH_ANNOUNCEMENT.getMessage());
    }

    public Announcement create(final Announcement ann) {
        ann.setBookId(this.bookRepo.findById(ann.getBookId().getId()));
        ann.setOwnerId(this.userRepo.findById(ann.getOwnerId().getId()));
        ann.setAdminId(this.userRepo.findById(ann.getAdminId().getId()));
        return this.announcementRepo.save(ann);
    }

    public Announcement update(final Announcement ann) {
        this.announcementRepo.updateById(ann.getDescription(), ann.getDate(), ann.isStatus(),
            ann.getBookId().getId(), ann.getAdminId().getId(),
            ann.getOwnerId().getId(), ann.getId());
        return ann;
    }

    public Announcement publish(final Announcement ann) {
        this.announcementRepo.publish(ann.getId());
        return ann;
    }
}
