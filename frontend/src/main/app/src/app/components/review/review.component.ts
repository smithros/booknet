import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {Book} from "../../models/book";
import {Review} from "../../models/review";
import {Subscription} from "rxjs";
import {ApiService} from "../../services/api-service/api.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user-service/user.service";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})

export class ReviewComponent implements OnInit {
  @Input() public review: Review;
  @Input() public book: Book;
  public user: User = new User();
  private subscription: Subscription;

  constructor(private apiService: ApiService,
              private route: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit() {
    this.getReview();
  }

  getReview(): void {
    const id = +this.route.snapshot.paramMap.get('reviewId');
    this.subscription = this.apiService.getReviewById(id).subscribe(
      review => {
        this.review = new Review();
        this.review = review;
        this.subscription = this.apiService.getBookById(review.bookId).subscribe(
          book => {
            this.book = book
          });
        this.subscription = this.userService.getUser(review.userId.toString()).subscribe(
          user => this.user = user
        );

      }
    );
  }

  fillArray(grade: number) {
    return Array.from({length: grade}, (v, i) => i)
  }

  back() {
    this.apiService.back();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
