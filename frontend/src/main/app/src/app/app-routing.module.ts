import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/authorization/login/login.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {RegisterComponent} from './components/authorization/register/register.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {BookListComponent} from "./components/book-list/book-list.component";
import {BookComponent} from "./components/book/book.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user/get/all', component: UserListComponent},
  {path: 'home', component: LandingComponent},
  {path: '', component: LandingComponent, pathMatch: 'full'},
  {path: 'book', component: BookComponent},
  {path: 'books/book/:bookId', component: BookComponent},
  {path: 'books', component: BookListComponent},
  {path: 'error', component: ErrorPageComponent},
  {path: '**', component: ErrorPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
