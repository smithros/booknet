import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/authorization/login/login.component';
import {AuthenticationService} from './services/authentication/authentication.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UserListComponent} from './components/user-list/user-list.component';
import {HttpClientModule} from '@angular/common/http';
import {RegisterComponent} from './components/authorization/register/register.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {AnnouncementComponent} from './components/announcement/announcement.component';
import {AnnouncementListComponent} from './components/announcement-list/announcement-list.component';
import {AnnouncementProposeComponent} from './components/announcement-propose/announcement-propose.component';
import {BookComponent} from './components/book/book.component';
import {BookListComponent} from './components/book-list/book-list.component';
import {ReviewComponent} from './components/review/review.component';
import {SearchComponent} from './components/search/search.component';
import {BookImgComponent} from './components/book-img/book-img.component';
import {BookFileComponent} from './components/book-file/book-file.component';
import {AddBookComponent} from './components/add-book/add-book.component';
import {UserBooksComponent} from './components/user/user-books/user-books.component';
import {UserReadBooksComponent} from './components/user/user-read-books/user-read-books.component';
import {UserFavouriteBooksComponent} from './components/user/user-favourite-books/user-favourite-books.component';
import {UserProfileComponent} from './components/user/user-profile/user-profile.component';
import {DeactivateAccountComponent} from './components/user/deactivate-account/deactivate-account.component';
import {EditProfileComponent} from './components/user/edit-profile/edit-profile.component';
import {UserMenuComponent} from './components/user/user-menu/user-menu.component';
import {UserSettingsComponent} from './components/user/user-settings/user-settings.component';
import {ViewProfileComponent} from './components/user/view-profile/view-profile.component';
import {ReviewListComponent} from "./components/review-list/review-list.component";
import {CKEditorModule} from "@ckeditor/ckeditor5-angular";
import {AboutComponent} from './components/about/about.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    RegisterComponent,
    LandingComponent,
    ErrorPageComponent,
    NavigationComponent,
    SidebarComponent,
    AnnouncementComponent,
    AnnouncementListComponent,
    AnnouncementProposeComponent,
    BookComponent,
    BookListComponent,
    ReviewComponent,
    ReviewListComponent,
    SearchComponent,
    BookImgComponent,
    BookFileComponent,
    AddBookComponent,
    UserBooksComponent,
    UserReadBooksComponent,
    UserFavouriteBooksComponent,
    UserProfileComponent,
    DeactivateAccountComponent,
    EditProfileComponent,
    UserMenuComponent,
    UserSettingsComponent,
    ViewProfileComponent,
    AboutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    CKEditorModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
