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

import java.util.Optional;
import com.kpi.booknet.booknet.model.Review;
import com.kpi.booknet.booknet.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(final ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addReview(@RequestBody final Review review) {
        return Optional.ofNullable(reviewService.createReview(review))
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/detail")
    public ResponseEntity<?> getReviewById(@RequestParam(name = "review") final long reviewId) {
        Review response = reviewService.getReviewById(reviewId);
        return Optional.ofNullable(response)
            .map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getReviewsOfBook(@RequestParam(name = "book") final long bookId) {
        return ResponseEntity.ok(reviewService.getReviewOfBook(bookId));
    }

    @RequestMapping(value = "/accepted", method = RequestMethod.GET)
    public ResponseEntity<?> getAcceptedReviews(@RequestParam(name = "book") final long bookId) {
        return ResponseEntity.ok(reviewService.getAcceptedReview(bookId));
    }

    @RequestMapping(value = "/notaccepted", method = RequestMethod.GET)
    public ResponseEntity<?> getNotAcceptedReviews(@RequestParam(name = "book") final long bookId) {
        return ResponseEntity.ok(reviewService.getNotAcceptedReview(bookId));
    }

    @RequestMapping(value = "/accept")
    public void acceptReview(@RequestBody Review review) {
        reviewService.acceptReview(review);
    }

    @RequestMapping(value = "/delete")
    public void deleteReviewById(@RequestParam(name = "review") final long reviewId) {
        reviewService.deleteReviewById(reviewId);
    }
}
