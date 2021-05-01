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
  {path: 'userBooks/:bookId', component: BookComponent},
  {path: 'userBooks/book/:bookId', component: BookComponent},
  {path: 'userBooks/read', component: UserReadBooksComponent},
  {path: 'userBooks/favourite', component: UserFavouriteBooksComponent},
  {path: 'userBooks/read/:bookId', component: BookComponent},
  {path: 'userBooks/favourite/:bookId', component: BookComponent},
  {path: 'userBooks/read/book/:bookId', component: BookComponent},
  {path: 'userBooks/favourite/book/:bookId', component: BookComponent},
  {path: 'error', component: ErrorPageComponent},
  {path: '**', component: ErrorPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
