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

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.kpi.booknet.booknet.dto.ReviewDto;
import com.kpi.booknet.booknet.exceptions.BookNetException;
import com.kpi.booknet.booknet.exceptions.ErrorType;
import com.kpi.booknet.booknet.model.Review;
import com.kpi.booknet.booknet.repos.BookRepository;
import com.kpi.booknet.booknet.repos.ReviewRepository;
import com.kpi.booknet.booknet.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;
    private final ModelMapper mapper;

    public Review getReviewById(final long id) {
        return this.reviewRepo.findById(id);
    }

    public ReviewDto createReview(final ReviewDto review) {
        review.setDate(new Date());
        review.setBookId(this.bookRepo.findById(review.getBookId()).get().getId());
        review.setUserId(this.userRepo.findById(review.getUserId()).get().getId());
        review.setAdminId(this.userRepo.findById(review.getAdminId()).get().getId());
        Review ent = this.convertToEntity(review);
        this.reviewRepo.save(ent);
        return review;
    }

    public List<Review> getReviewOfBook(final long bookId) {
        return this.reviewRepo.findReviewsByBookId(this.bookRepo.findById(bookId));
    }

    public void acceptReview(final ReviewDto review) {
        Review ent = this.convertToEntity(review);
        if (this.getReviewById(ent.getId()) != null) {
            ent.setStatus(true);
            this.reviewRepo.acceptReview(ent.getId(), ent.getAdminId());
        }
    }

    public List<ReviewDto> getAcceptedReviews(final long bookId) {
        final List<Review> lst =
            this.reviewRepo.findReviewsByStatusIsTrueAndBookId(this.bookRepo.findById(bookId));
        return lst.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ReviewDto> getNotAcceptedReviews(final long bookId) {
        final List<Review> lst =
            this.reviewRepo.findReviewsByStatusIsFalseAndBookId(this.bookRepo.findById(bookId));
        return lst.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public void deleteReviewById(final long reviewId) {
        if (this.getReviewById(reviewId) != null) {
            this.reviewRepo.deleteById(reviewId);
        } else {
            throw new BookNetException(ErrorType.NO_REVIEW_WITH_SUCH_ID.getMessage());
        }
    }

    public List<Review> getAllReviews() {
        return this.reviewRepo.findAll();
    }

    private ReviewDto convertToDto(final Review ann) {
        ReviewDto dto = this.mapper.map(ann, ReviewDto.class);
        dto.setId(ann.getId());
        dto.setDate(ann.getDate());
        dto.setGrade(ann.getGrade());
        dto.setText(ann.getText());
        dto.setAdminId(ann.getAdminId().getId());
        dto.setBookId(ann.getBookId().getId());
        dto.setUserId(ann.getUserId().getId());
        return dto;
    }

    private Review convertToEntity(final ReviewDto ann) {
        Review ent = this.mapper.map(ann, Review.class);
        ent.setId(ann.getId());
        ent.setDate(ann.getDate());
        ent.setGrade(ann.getGrade());
        ent.setText(ann.getText());
        ent.setAdminId(this.userRepo.findById(ann.getAdminId()).get());
        ent.setBookId(this.bookRepo.findById(ann.getBookId()).get());
        ent.setUserId(this.userRepo.findById(ann.getUserId()).get());
        return ent;
    }
}
