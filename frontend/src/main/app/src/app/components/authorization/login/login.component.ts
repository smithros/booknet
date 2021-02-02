import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Subscription} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {StorageService} from '../../../services/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  isError: boolean = false;
  loginGroup!: FormGroup;
  subscription!: Subscription;
  loginSubscription!: Subscription;

  constructor(private http: HttpClient,
              public authService: AuthenticationService,
              private router: Router,
              private storageService: StorageService) {
    if (storageService.getUser()) {
      this.router.navigateByUrl('/').then();
    }
  }

  get username() {
    return this.loginGroup.get('username');
  }

  get password() {
    return this.loginGroup.get('password');
  }

  ngOnInit() {
    this.loginGroup = new FormGroup({
      username: new FormControl(''),
      password: new FormControl('')
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    if (this.loginSubscription) {
      this.loginSubscription.unsubscribe();
    }
  }

  public login(): void {
    this.isError = false;
    let username = this.username!.value;
    let password = this.password!.value;

    this.loginSubscription = this.authService.login(username, password).subscribe(
      user => {
        this.storageService.setUser(user);
        sessionStorage.setItem('user', JSON.stringify(user));
        this.router.navigateByUrl('/');
      });
  }
}
