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

import java.util.Date;
import java.util.List;
import com.kpi.booknet.booknet.model.Review;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepo;
    private final BookRepository bookRepo;

    @Autowired
    public ReviewService(final ReviewRepository reviewRepo, final BookRepository bookRepo) {
        this.reviewRepo = reviewRepo;
        this.bookRepo = bookRepo;
    }

    public Review getReviewById(final long id) {
        return this.reviewRepo.findById(id);
    }

    public Review createReview(final Review review) {
        review.setDate(new Date());
        return this.reviewRepo.save(review);
    }

    public List<Review> getReviewOfBook(final long bookId) {
        return this.reviewRepo.findReviewsByBookId(this.bookRepo.findById(bookId));
    }

    public void acceptReview(final Review review) {
        this.reviewRepo.acceptReview(review.getAdminId().getId(), review.getId());
    }

    public List<Review> getAcceptedReview(final long bookId) {
        return this.reviewRepo.findReviewsByStatusIsTrueAndBookId(this.bookRepo.findById(bookId));
    }

    public List<Review> getNotAcceptedReview(final long bookId) {
        return this.reviewRepo.findReviewsByStatusIsFalseAndBookId(this.bookRepo.findById(bookId));
    }

    public void deleteReviewById(final long reviewId) {
        if (this.reviewRepo.findById(reviewId) != null) {
            this.reviewRepo.deleteById(reviewId);
        }
    }

    public List<Review> getAllReview() {
        return this.reviewRepo.findAll();
    }
}
