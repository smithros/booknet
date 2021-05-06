import {Component, Input, OnInit} from '@angular/core';
import {Review} from "../../models/review";
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {FormBuilder, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user-service/user.service";
import {Book} from "../../models/book";
import {Subscription} from "rxjs";
import {StorageService} from "../../services/storage/storage.service";
import {ApiService} from "../../services/api-service/api.service";

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})

export class ReviewListComponent implements OnInit {
  public Editor = ClassicEditor;
  public acceptedReviews: Review[] = [];
  public notAcceptedReviews: Review[] = [];
  @Input() public book: Book;
  public addReviewVisible: boolean;
  public bookId: number;

  public successCreatedReview:boolean = false;
  public errorCreatedReview:boolean = false;
  public createdReview: Review = new Review();
  public notAcceptedReviewVisible:boolean = false;
  public createdReviewForm = this.formBuilder.group({
    grade: [5, Validators.required],
    text: ['', Validators.required]
  });

  private subscription: Subscription;

  constructor(private commonService: ApiService,
              private route: ActivatedRoute,
              private router: Router,
              private  userService: UserService,
              private storage: StorageService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
    this.addReviewVisible = true;
    this.ngOnChanges();
  }

  checkAdmin(){
    if(this.storage.getUser()!=null){
      //if (this.storage.getUser().userRole == 'moderator') {
        this.getNotAcceptedReviews();
        this.notAcceptedReviewVisible = true;
      //}
    }
  }

  ngOnChanges() {
    if (this.book != null) {
      this.getAcceptedReviews();
      this.checkAdmin();
    }
  }

  getAcceptedReviews(): void {
    this.subscription = this.commonService.getAcceptedReviews(this.book.id).subscribe(
      res => {
        this.acceptedReviews = res;
        this.acceptedReviews.forEach(
          review => {
            this.subscription = this.userService.getUser(review.userId.toString()).subscribe(
              res => {
                review.username = res.name;
              });
          }
        );
      }
    );
  }

  getNotAcceptedReviews(): void {
    this.subscription = this.commonService.getNotAcceptedReviews(this.book.id).subscribe(
      res => {
        this.notAcceptedReviews = res;
        this.notAcceptedReviews.forEach(
          review => {
            this.subscription = this.userService.getUser(review.userId.toString()).subscribe(
              res => {
                review.username = res.name;
              });
          }
        );
      }
    )
  }

  acceptReview(review: Review): void {
    this.checkUser();
    review.adminId = this.storage.getUser().id;
    this.subscription = this.commonService.acceptReview(review).subscribe(
      res => {
        this.acceptedReviews.push(review);
        this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review));
      }
    );
  }

  createReview(): void {
    this.checkUser();
    const newCreatedReview: Review = Object.assign({}, this.createdReview);
    newCreatedReview.bookId = this.bookId;
    newCreatedReview.userId = this.storage.getUser().id;
    newCreatedReview.adminId = this.storage.getUser().id;
    this.subscription = this.commonService.createReview(newCreatedReview)
      .subscribe(res => {
          this.successCreatedReview = true;
          this.subscription = this.userService.getUser(res.userId.toString()).subscribe(
            user => {
              res.username = user.name;
            }
          );
          this.createdReview.text = "";
          this.createdReview.grade = null;
          this.notAcceptedReviews.push(res);
        },
        err => {
          this.errorCreatedReview = true;
        }
      );
  }

  deleteReviewById(review: Review) {
    this.subscription = this.commonService.deleteReviewById(review).subscribe(
      res => {
        this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review), 1)
      }
    );
  }

  fillArray(grade: number) {
    return Array.from({length: grade}, (v, i) => i)
  }

  checkUser() {
    if (this.storage.getUser() == null) {
      this.router.navigate(['/login']);
    }
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
