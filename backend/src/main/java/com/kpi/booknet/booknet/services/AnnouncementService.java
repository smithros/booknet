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
import java.util.stream.Collectors;
import com.kpi.booknet.booknet.dto.AnnouncementDto;
import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.Announcement;
import com.kpi.booknet.booknet.repos.AnnouncementRepository;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AnnouncementService {

    private final AnnouncementRepository announ;

    private final BookRepository books;

    private final UserRepository users;

    private final ModelMapper mapper;

    public Announcement getById(final long id) {
        log.info("Get announcement with id: {}", id);
        return this.announ.findById(id);
    }

    public List<Announcement> getAll() {
        return (List<Announcement>) this.announ.findAll();
    }

    public List<AnnouncementDto> getPublished() {
        final List<Announcement> lst = this.announ.findAnnouncementsByStatusTrue();
        return lst.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<Announcement> getUnpublished() {
        return this.announ.findAnnouncementsByStatusFalse();
    }

    public Announcement delete(final long id) {
        if (this.getById(id) != null) {
            log.info("Deleted announcement with id: {}", id);
            return this.announ.deleteById(id);
        }
        throw new BookNetException(ErrorType.NO_SUCH_ANNOUNCEMENT.getMessage());
    }

    public AnnouncementDto create(final AnnouncementDto ann) {
        ann.setBookId(this.books.findById(ann.getBookId()).get().getId());
        ann.setOwnerId(this.users.findById(ann.getOwnerId()).get().getId());
        ann.setAdminId(this.users.findById(ann.getAdminId()).get().getId());
        final Announcement ent = this.convertToEntity(ann);
        log.info("Created announcement: {}", ent);
        this.announ.save(ent);
        return ann;
    }

    public Announcement update(final Announcement ann) {
        this.announ.updateById(ann.getDescription(), ann.getDate(), ann.isStatus(),
            ann.getBookId().getId(), ann.getAdminId().getId(),
            ann.getOwnerId().getId(), ann.getId());
        log.info("Updated announcement: {}", ann.getId());
        return ann;
    }

    public Announcement publish(final Announcement ann) {
        log.info("Published announcement: {}", ann);
        this.announ.publish(ann.getId());
        return ann;
    }

    private AnnouncementDto convertToDto(final Announcement ann) {
        final AnnouncementDto dto = this.mapper.map(ann, AnnouncementDto.class);
        dto.setId(ann.getId());
        dto.setDescription(ann.getDescription());
        dto.setAdminId(ann.getAdminId().getId());
        dto.setOwnerId(ann.getOwnerId().getId());
        dto.setBookId(ann.getBookId().getId());
        dto.setDate(ann.getDate());
        dto.setStatus(ann.getBookId().isStatus());
        return dto;
    }

    private Announcement convertToEntity(final AnnouncementDto ann) {
        final Announcement ent = this.mapper.map(ann, Announcement.class);
        ent.setId(ann.getId());
        ent.setDescription(ann.getDescription());
        ent.setOwnerId(this.users.findById(ann.getOwnerId()).get());
        ent.setAdminId(this.users.findById(ann.getAdminId()).get());
        ent.setBookId(this.books.findById(ann.getBookId()).get());
        ent.setDate(ann.getDate());
        ent.setStatus(ann.isStatus());
        return ent;
    }
}
