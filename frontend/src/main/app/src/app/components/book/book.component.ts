import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {Author} from "../../models/author";
import {Subscription} from "rxjs";
import {ApiService} from "../../services/api-service/api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {UserBook} from "../../models/userBook";
import {Genre} from "../../models/genre";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  @Input() public book: Book;
  public authors: Author[] = [];
  public genres: Genre[] = [];
  public suggestionBook: Book[] = [];
  public bookId: any;

  public addAnnouncementVisible: boolean = false;
  public userBookButtonVisible: boolean = false;

  public userAddedBook: boolean = false;
  public userAddedToRead: boolean = false;
  public userAddedToFav: boolean = false;
  public userFavReadVisible: boolean = false;

  private subscription: Subscription;

  constructor(private apiService: ApiService,
              private route: ActivatedRoute,
              private router: Router,
              private storage: StorageService,
  ) {
  }

  ngOnInit() {
    this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
    this.getBook(this.bookId);
    this.checkUser()
  }

  navigateToBook(bookId: number) {
    this.router.navigate(['books/book', bookId]);
    this.suggestionBook = [];
    this.ngOnInit();
  }

  getBook(bookId: number): void {
    this.apiService.getBookById(bookId).subscribe(
      res => {
        this.book = res;
        this.apiService.getGenreByBookId(this.bookId).subscribe(
          genres => {
            this.book.genres = [];
            this.genres = genres;
            genres.forEach(genre => this.book.genres.push(genre));
          });
        this.apiService.getAuthorsByBookId(this.bookId).subscribe(
          authors => {
            this.book.authors = [];
            this.authors = authors;
            authors.forEach(author => this.book.authors.push(author));
          }
        );
        this.apiService.getImageByBook(this.book).subscribe(
          img => {
            let reader = new FileReader();
            reader.addEventListener("load", () => {
              this.book.photoURL = reader.result;
            }, false);
            if (img) {
              reader.readAsDataURL(img);
            }
          }
        )
      }/*,
        err => {
          this.router.navigateByUrl('/error');
        }*/
    );
  }

  addBookToFavourite(bookId: number) {
    this.apiService.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    userBook.bookId = bookId;

    this.userAddedToFav = true;
    this.subscription = this.apiService.markUserBookAsFavourite(userBook).subscribe(
      res => {
        console.log("add to fav book: " + JSON.stringify(res));
      }
    );
  }

  addBookToRead(bookId: number) {
    this.apiService.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    userBook.bookId = bookId;

    this.userAddedToRead = true;
    this.subscription = this.apiService.markUserBookAsRead(userBook).subscribe(
      res => {
        console.log("add to read book: " + JSON.stringify(res));
      }
    );
  }

  removeBookFromFav(bookId: number) {
    this.apiService.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    userBook.bookId = bookId;

    this.userAddedToFav = false;
    this.subscription = this.apiService.removeFromFavourite(userBook).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  removeBookFromRead(bookId: number) {
    this.apiService.checkPresentUser();

    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    userBook.bookId = bookId;

    this.userAddedToRead = false;
    this.subscription = this.apiService.removeFromRead(userBook).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  addBookToUser(bookId: number) {
    this.apiService.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.bookId = bookId;
    userBook.userId = this.storage.getUser().id;

    this.userAddedBook = true;
    this.userFavReadVisible = true;
    this.subscription = this.apiService.addBookToUser(userBook).subscribe(
      res => {
        console.log("add book: " + JSON.stringify(res));
      }
    );
  }

  deleteBookFromUser(bookId: number) {
    this.apiService.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    userBook.bookId = bookId;
    this.userAddedBook = false;
    this.userFavReadVisible = false;
    this.subscription = this.apiService.deleteFromAdded(userBook).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  checkUser(){
    if (this.storage.getUser()!=null) {
      if (this.storage.getUser().role == 'MODERATOR') {
        this.addAnnouncementVisible = true;
      }
      this.userBookButtonVisible = true;
      this.checkButton();
    }
  }

  checkButton(){
    let userBook:UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    userBook.bookId = this.bookId;
    this.subscription = this.apiService.getAllUserBooks(userBook).subscribe(
      res => {
        this.userAddedBook = res.find(book => book.id == this.book.id) != null;
        this.userFavReadVisible = res.find(book => book.id == this.book.id) != null;

      });
    this.subscription = this.apiService.getAllFavouriteBooks(userBook).subscribe(
      res=>{
        this.userAddedToFav = res.find(book => book.id == this.book.id) != null;
        this.userFavReadVisible = res.find(book => book.id == this.book.id) != null;
      }
    );
    this.subscription = this.apiService.getAllReadBooks(userBook).subscribe(
      res=>{
        this.userAddedToRead = res.find(book => book.id == this.book.id) != null;
        this.userFavReadVisible = res.find(book => book.id == this.book.id) != null;
      }
    );
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
