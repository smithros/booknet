import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Book} from "../../models/book";
import {Observable} from 'rxjs';
import {Announcement} from "../../models/announcement";
import {User} from "../../models/user";
import {Review} from "../../models/review";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {UserBook} from "../../models/userBook";
import {StorageService} from "../storage/storage.service";
import {Router} from "@angular/router";
import {Location} from '@angular/common';
import {BookFilter} from "../../models/bookFilter";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private url: string = environment.apiBaseUrl;
  private booksUrl: string = `${this.url}/book`;
  private announcementsUrl: string = `${this.url}/announcements`;
  private reviewsUrl: string = `${this.url}/review`;
  private userBookUrl: string = `${this.url}/userBook`;

  constructor(private http: HttpClient,
              private storage: StorageService,
              private router: Router,
              private location: Location) {
  }

  getBooks(): Observable<Book[]> {
    const url = `${this.booksUrl}/all`;
    return this.http.get<Book[]>(url);
  }

  getBookById(id: number): Observable<Book> {
    const url = `${this.booksUrl}/${id}`;
    return this.http.get<Book>(url);
  }

  createBook(book: Book): Observable<Book> {
    const url = `${this.booksUrl}`;
    return this.http.post<Book>(url, book);
  }

  getAnnouncements(): Observable<Announcement[]> {
    const url = `${this.announcementsUrl}`;
    return this.http.get<Announcement[]>(url);
  }

  createAnnouncement(announcement: Announcement): Observable<Announcement> {
    const url = `${this.announcementsUrl}/newAnnouncement`;
    return this.http.post<Announcement>(url, announcement);
  }

  getAnnouncementsUnPublish(): Observable<Announcement[]> {
    const url = `${this.announcementsUrl}/new`;
    return this.http.get<Announcement[]>(url);
  }

  publishAnnouncement(announcement: Announcement): Observable<Announcement> {
    const url = `${this.announcementsUrl}/publish`;
    return this.http.post<Announcement>(url, announcement);
  }

  getAuthorsByBookId(bookId: number): Observable<Author[]> {
    const url = `${this.booksUrl}/authors/${bookId}`;
    const params = new HttpParams().set("id", bookId.toString());
    return this.http.get<Author[]>(url, {params: params});
  }

  getGenreByBookId(bookId: number): Observable<Genre[]> {
    const url = `${this.booksUrl}/genres/${bookId}`;
    const params = new HttpParams().set("id", bookId.toString());
    return this.http.get<Genre[]>(url, {params: params});
  }

  getAllAuthor(): Observable<Author[]> {
    const url = `${this.booksUrl}/authors`;
    return this.http.get<Author[]>(url);
  }

  getAllGenre(): Observable<Genre[]> {
    const url = `${this.booksUrl}/genres`;
    return this.http.get<Genre[]>(url);
  }

  getReviews(id: number): Observable<Review[]> {
    let body = new HttpParams()
      .set('book', id.toString());
    const url = `${this.reviewsUrl}/all`;
    return this.http.get<Review[]>(url, {params: body});
  }

  createReview(review: Review): Observable<Review> {
    console.log("createReview" + JSON.stringify(review));
    const url = `${this.reviewsUrl}`;
    return this.http.post<Review>(url, review);
  }

  getNotAcceptedReviews(bookId: number): Observable<Review[]> {
    const params = new HttpParams()
      .set('book', bookId.toString());
    const url = `${this.reviewsUrl}/notaccepted`;
    return this.http.get<Review[]>(url, {params: params});
  }

  getAcceptedReviews(bookId: number): Observable<Review[]> {
    const params = new HttpParams()
      .set('book', bookId.toString());
    const url = `${this.reviewsUrl}/accepted`;
    return this.http.get<Review[]>(url, {params: params});
  }

  acceptReview(review: Review): Observable<Review> {
    const url = `${this.reviewsUrl}/accept`;
    return this.http.post<Review>(url, review);
  }

  deleteReviewById(review: Review): Observable<Review> {
    const params = new HttpParams()
      .set('review', review.id.toString());
    const url = `${this.reviewsUrl}/delete`;
    return this.http.post<Review>(url, params);
  }

  getReviewById(reviewId: number) {
    const url = `${this.reviewsUrl}/detail`;
    const params = new HttpParams()
      .set('review', reviewId.toString());
    return this.http.get<Review>(url, {params: params});
  }

  addBookToUser(userBook: UserBook): Observable<UserBook> {
    const url = `${this.userBookUrl}/add`;
    return this.http.post<UserBook>(url, userBook);
  }

  getAllUserBooks(userBook: UserBook): Observable<Book[]> {
    const url = `${this.userBookUrl}/all`;
    const params = new HttpParams().set('userId', userBook.userId.toString());
    return this.http.get<Book[]>(url, {params: params});
  }

  getUserBookById(userBook: UserBook) {
    const url = `${this.userBookUrl}/getById`;
    const params = new HttpParams()
      .append('userId', userBook.userId.toString())
      .append('bookId', userBook.bookId.toString());
    return this.http.get<UserBook>(url, {params: params});
  }

  getAllUserBooksByUserId(userId: number): Observable<UserBook[]> {
    const params = new HttpParams().set('userId', userId.toString());
    const url = `${this.userBookUrl}/getAllById`;
    return this.http.post<UserBook[]>(url, {params: params});
  }

  markUserBookAsRead(userBook: UserBook): Observable<UserBook> {
    const url = `${this.userBookUrl}/mark_read`;
    return this.http.post<UserBook>(url, userBook);
  }

  markUserBookAsFavourite(userBook: UserBook): Observable<UserBook> {
    const url = `${this.userBookUrl}/mark_fav`;
    return this.http.post<UserBook>(url, userBook);
  }

  getAllFavouriteBooks(userBook: UserBook): Observable<Book[]> {
    const params = new HttpParams().set('userId', userBook.userId.toString());
    const url = `${this.userBookUrl}/all/favourite`;
    return this.http.get<Book[]>(url, {params: params});
  }

  getAllReadBooks(userBook: UserBook): Observable<Book[]> {
    const params = new HttpParams().set('userId', userBook.userId.toString());
    const url = `${this.userBookUrl}/all/read`;
    return this.http.get<Book[]>(url, {params: params});
  }

  removeFromFavourite(userBook: UserBook): Observable<UserBook> {
    const url = `${this.userBookUrl}/remove_fav`;
    return this.http.post<UserBook>(url, userBook);
  }

  removeFromRead(userBook: UserBook): Observable<UserBook> {
    const url = `${this.userBookUrl}/remove_read`;
    return this.http.post<UserBook>(url, userBook);
  }

  deleteFromAdded(userBook: UserBook): Observable<UserBook> {
    const url = `${this.userBookUrl}/delete`;
    return this.http.post<UserBook>(url, userBook);
  }

  makeSuggestion(userId: number): Observable<Book[]> {
    const url = `${this.booksUrl}/suggestion`;
    let params = new HttpParams().append('user', userId.toString());
    return this.http.post<Book[]>(url, params);
  }

  getMostRatedBooks(): Observable<Book[]> {
    const url = `${this.booksUrl}/rate`;
    return this.http.get<Book[]>(url);
  }

  getBooksByFilter(filter: BookFilter): Observable<Book[]> {
    const url = `${this.booksUrl}/filter`;
    return this.http.post<Book[]>(url, filter);
  }

  getImageByBook(book: Book) {
    const url = `${this.booksUrl}/bookImage`;
    return this.http.post(url, book, {responseType: 'blob'});
  }

  checkPresentUser() {
    if (this.storage.getUser() == null) {
      this.router.navigate(['/login']);
    }
  }

  back() {
    this.location.back()
  }
}
