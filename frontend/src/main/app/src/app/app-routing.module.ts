import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/authorization/login/login.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {RegisterComponent} from './components/authorization/register/register.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {LandingComponent} from './components/landing/landing.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user/get/all', component: UserListComponent},
  {path: 'home', component: LandingComponent},

  {path: '**', component: ErrorPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
