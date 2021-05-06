import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/authorization/login/login.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {RegisterComponent} from './components/authorization/register/register.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {BookListComponent} from "./components/book-list/book-list.component";
import {BookComponent} from "./components/book/book.component";
import {UserBooksComponent} from "./components/user/user-books/user-books.component";
import {UserReadBooksComponent} from "./components/user/user-read-books/user-read-books.component";
import {UserFavouriteBooksComponent} from "./components/user/user-favourite-books/user-favourite-books.component";
import {AddBookComponent} from "./components/add-book/add-book.component";
import {AnnouncementComponent} from "./components/announcement/announcement.component";
import {AnnouncementListComponent} from "./components/announcement-list/announcement-list.component";
import {CalendarComponent} from "./components/calendar/calendar.component";
import {AnnouncementProposeComponent} from "./components/announcement-propose/announcement-propose.component";
import {UserProfileComponent} from "./components/user/user-profile/user-profile.component";
import {ReviewComponent} from "./components/review/review.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user/get/all', component: UserListComponent},
  {path: 'home', component: LandingComponent},
  {path: '', component: LandingComponent, pathMatch: 'full'},
  {path: 'book', component: BookComponent},
  {path: 'books/book/:bookId', component: BookComponent},
  {path: 'books', component: BookListComponent},
  {path: 'userBooks', component: UserBooksComponent},
  {path: 'userBooks/book/:bookId', component: BookComponent},
  {path: 'userBooks/read', component: UserReadBooksComponent},
  {path: 'userBooks/read/book/:bookId', component: BookComponent},
  {path: 'userBooks/favourite', component: UserFavouriteBooksComponent},
  {path: 'userBooks/favourite/book/:bookId', component: BookComponent},
  {path: 'userBooks/favourite/books', component: BookListComponent},
  {path: 'userBooks/read/books', component: BookListComponent},
  {path: 'userBooks/books', component: BookListComponent},
  {path: 'userBooks/favourite/books/book/:bookId', component: BookListComponent},
  {path: 'userBooks/read/books/book/:bookId', component: BookListComponent},
  {path: 'userBooks/books/book/:bookId', component: BookComponent},
  {path: 'books/addBook', component: AddBookComponent},
  {path: 'books/:bookId/announcement', component: AnnouncementComponent},
  {path: 'announcement', component: AnnouncementComponent},
  {path: 'announcementlist', component: AnnouncementListComponent},
  {path: 'announcementpublish', component: AnnouncementProposeComponent},
  {path: 'calendar', component: CalendarComponent},
  {path: 'user', component: UserProfileComponent},
  {path: 'user/:id', component: UserProfileComponent},
  {path: 'user/:id/books', component: BookListComponent},
  {path: 'user/:id/books/book/:id', component: BookComponent},
  {path: 'books/book/:bookId/review/:reviewId', component: ReviewComponent},
  {path: 'error', component: ErrorPageComponent},
  {path: '**', component: ErrorPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
