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
import { AnnouncementComponent } from './components/announcement/announcement.component';
import { AnnouncementListComponent } from './components/announcement-list/announcement-list.component';
import { AnnouncementProposeComponent } from './components/announcement-propose/announcement-propose.component';
import { BookComponent } from './components/book/book.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { ReviewComponent } from './components/review/review.component';
import { ReviewListComponent } from './components/review-list/review-list.component';
import { SearchComponent } from './components/search/search.component';
import { UploadFileComponent } from './components/upload-file/upload-file.component';


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
    CalendarComponent,
    ReviewComponent,
    ReviewListComponent,
    SearchComponent,
    UploadFileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
