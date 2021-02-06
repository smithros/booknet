import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/authorization/login/login.component';
import {AuthenticationService} from './services/authentication/authentication.service';
import {ReactiveFormsModule} from '@angular/forms';
import {UserListComponent} from './components/user-list/user-list.component';
import {HttpClientModule} from '@angular/common/http';
import {RegisterComponent} from './components/authorization/register/register.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    RegisterComponent,
    LandingComponent,
    ErrorPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
