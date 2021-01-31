import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/authorization/login/login.component';
import {AuthenticationService} from './services/authentication/authentication.service';
import {ReactiveFormsModule} from '@angular/forms';
import {StorageService} from './services/storage/storage.service';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserService} from './services/user-service/user.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UserService, StorageService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
