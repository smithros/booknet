import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {Author} from "../../models/author";
import {Subscription} from "rxjs";
import {Genre} from "../../models/genre";
import {UserBook} from "../../models/userBook";
import {Item} from "../../models/item";
import {ApiService} from "../../services/api-service/api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {BookFilter} from "../../models/bookFilter";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  genres: Genre[] = [];
  authors: Author[] = [];
  books: Book[] = [];
  userBooks: UserBook[] = [];
  @Input() userBookList: Book[] = [];
  @Input() userFavBookList: Book[] = [];
  @Input() userReadBookList: Book[] = [];
  book: Book;

  addBookVisible: boolean = false;
  userId: any;
  emptyBookList: boolean = false;
  emptyFavList: boolean = false;
  emptyReadList: boolean = false;

  selectedAuthors: Item[] = [];
  selectedGenres: Item[] = [];
  filterGenres: Item[] = [];
  filterAuthors: Item[] = [];
  searchGenre;
  searchAuthor;
  bookFilter: BookFilter = new BookFilter();
  historyFilter: BookFilter = new BookFilter();

  private subscription: Subscription;

  constructor(public apiService: ApiService,
              public route: ActivatedRoute,
              public router: Router,
              public storage: StorageService) {
  }

  ngOnInit() {
    this.checkUser();
    this.getBooks();
    this.getAllAuthor();
    this.getAllGenre();
    this.checkHistoryFilter();
  }

  checkUser() {
    if (this.storage.getUser() != null) {
      this.getUsersBookList();
      this.getAllReadBooks();
      this.getAllFavouriteBooks();
      this.checkModerator();
    }
  }

  checkModerator() {
    if (this.storage.getUser().role == 'MODERATOR') {
      this.addBookVisible = true;
    }
  }

  getUsersBookList() {
    this.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    this.subscription = this.apiService.getAllUserBooks(userBook).subscribe(
      res => {
        this.userBookList = res;
        if (this.userBookList.length == 0) {
          this.emptyBookList = true;
          this.subscription = this.apiService.getMostRatedBooks().subscribe(
            bookList => {
              this.userBookList = bookList;
            }
          );
        }
      },
    );
  }

  getAllFavouriteBooks() {
    this.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    this.subscription = this.apiService.getAllFavouriteBooks(userBook).subscribe(
      res => {
        this.userFavBookList = res;
        if (this.userFavBookList.length == 0) {
          this.emptyFavList = true;
          this.subscription = this.apiService.getMostRatedBooks().subscribe(
            favList => {
              this.userFavBookList = favList;
            }
          );
        }
      },
    );
  }

  getAllReadBooks() {
    this.checkPresentUser();
    let userBook: UserBook = new UserBook();
    userBook.userId = this.storage.getUser().id;
    this.subscription = this.apiService.getAllReadBooks(userBook).subscribe(
      res => {
        this.userReadBookList = res;
        if (this.userReadBookList.length == 0) {
          this.emptyReadList = true;
          this.subscription = this.apiService.getMostRatedBooks().subscribe(
            readList => {
              this.userReadBookList = readList;
            }
          );
        }
      },
    );
  }

  getAllAuthor() {
    this.apiService.getAllAuthor().subscribe(
      res => {
        this.authors = res;
        this.authors.forEach(author => {
          this.selectedAuthors.push({name: author.name, selected: false});
        });
      },
      err => {
        console.log(err)
      }
    );
  }

  getAllGenre() {
    this.apiService.getAllGenre().subscribe(
      res => {
        this.genres = res;
        this.genres.forEach(genre => {
          this.selectedGenres.push({name: genre.desc, selected: false});
        });
      },
      err => {
        console.log(err)
      }
    );
  }

  checkHistoryFilter() {
    this.historyFilter = this.storage.getFilter();
  }

  onFilterChange(event: any) {
    this.searchByFilter();
  }

  searchByFilter() {
    this.bookFilter.authors = [];
    this.bookFilter.genres = [];

    this.filterGenres = this.selectedGenres
      .filter(v => v.selected);
    this.filterAuthors = this.selectedAuthors
      .filter(v => v.selected);

    this.filterGenres.forEach(genre => {
      this.bookFilter.genres.push(genre.name);
    });
    this.filterAuthors.forEach(author => {
      this.bookFilter.authors.push(author.name);
    });

    this.historyFilter.genres.push(...(this.bookFilter.genres || []));
    this.historyFilter.authors.push(...(this.bookFilter.authors || []));
    this.storage.setFilter(this.historyFilter);
    this.books = [];
    this.apiService.getBooksByFilter(this.bookFilter).subscribe(
      res => {
        this.books = res;
        this.books.forEach(book => {
          this.apiService.getAuthorsByBookId(book.id).subscribe(
            authors => book.authors = authors
          );
          this.apiService.getGenreByBookId(book.id).subscribe(
            genres => book.genres = genres
          )
        })
      },
      error => console.log('Error in book filter')
    );
  }

  resetFilter() {
    this.bookFilter.header = "";
    this.bookFilter.authors = [];
    this.bookFilter.genres = [];
    this.filterGenres = [];
    this.filterAuthors = [];
    this.selectedGenres.forEach(genre => genre.selected = false);
    this.selectedAuthors.forEach(author => author.selected = false);
    this.getBooks();
  }

  getBooks(): void {
    this.apiService.getBooks().subscribe(
      res => {
        this.books = res;
        this.books.forEach(book => {
          this.apiService.getAuthorsByBookId(book.id).subscribe(
            authors => book.authors = authors
          );
          this.apiService.getGenreByBookId(book.id).subscribe(
            genres => book.genres = genres
          );
        });
      },
      err => {
        this.router.navigateByUrl('/error');
      }
    );
  }

  checkPresentUser() {
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
