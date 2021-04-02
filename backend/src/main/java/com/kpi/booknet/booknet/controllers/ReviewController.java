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

import java.util.Optional;
import com.kpi.booknet.booknet.model.Review;
import com.kpi.booknet.booknet.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/review")
@AllArgsConstructor
public final class ReviewController {

    private final ReviewService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addReview(@RequestBody final Review review) {
        return Optional.ofNullable(this.service.createReview(review))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable(name = "id") final long reviewId) {
        Review response = this.service.getReviewById(reviewId);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getReviewsOfBook(@RequestParam(name = "book") final long bookId) {
        return ResponseEntity.ok(this.service.getReviewOfBook(bookId));
    }

    @RequestMapping(value = "/accepted", method = RequestMethod.GET)
    public ResponseEntity<?> getAcceptedReviews(@RequestParam(name = "book") final long bookId) {
        return ResponseEntity.ok(this.service.getAcceptedReviews(bookId));
    }

    @RequestMapping(value = "/notaccepted", method = RequestMethod.GET)
    public ResponseEntity<?> getNotAcceptedReviews(@RequestParam(name = "book") final long bookId) {
        return ResponseEntity.ok(this.service.getNotAcceptedReviews(bookId));
    }

    @RequestMapping(value = "/accept/{id}")
    public void acceptReview(@PathVariable(name = "id") final long reviewId) {
        this.service.acceptReview(reviewId);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteReviewById(@PathVariable(name = "id") final long reviewId) {
        this.service.deleteReviewById(reviewId);
    }
}
