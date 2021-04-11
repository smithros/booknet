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

package com.kpi.booknet.booknet.controllers;

import java.util.List;
import java.util.Optional;
import com.kpi.booknet.booknet.model.Announcement;
import com.kpi.booknet.booknet.services.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/announcements")
@AllArgsConstructor
public final class AnnouncementController {

    private final AnnouncementService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Announcement>> getPublishedAnnouncements() {
        final List<Announcement> response = this.service.getPublished();
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ResponseEntity<List<Announcement>> getUnpublishedAnnouncements() {
        final List<Announcement> response = this.service.getUnpublished();
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        final List<Announcement> response = this.service.getAll();
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable("id") final long id) {
        final Announcement response = this.service.getById(id);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/newAnnouncement", method = RequestMethod.POST)
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody final Announcement ann) {
        final Announcement response = this.service.create(ann);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<Announcement> publishAnnouncement(@RequestBody final Announcement ann) {
        final Announcement response = this.service.publish(ann);
        return Optional.ofNullable(this.service.publish(ann))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<Announcement> deleteAnnouncement(@PathVariable("id") final long id) {
        final Announcement response = this.service.delete(id);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Announcement> updateAnnouncement(@RequestBody final Announcement ann) {
        final Announcement response = this.service.update(ann);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
