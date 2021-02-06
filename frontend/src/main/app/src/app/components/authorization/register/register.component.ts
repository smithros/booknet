import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {StorageService} from '../../../services/storage/storage.service';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {UserService} from '../../../services/user-service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public regGroup!: FormGroup;

  public regSubscription!: Subscription;
  public securitySubscription!: Subscription;

  public isError: boolean;

  constructor(private http: HttpClient,
              private router: Router,
              private storage: StorageService,
              private authenticationService: AuthenticationService,
              private userService: UserService
  ) {
    this.isError = false;
    if (storage.getUser()) {
      this.router.navigateByUrl('/').then();
    }
  }

  public get username() {
    return this.regGroup.get('username');
  }

  public get password() {
    return this.regGroup.get('password');
  }

  public get email() {
    return this.regGroup.get('email');
  }

  public get confirmPassword() {
    return this.regGroup.get('confirmPassword');
  }

  public ngOnInit() {
    this.regGroup = new FormGroup({
      username: new FormControl('',
        [Validators.minLength(4), Validators.pattern(this.userService.inputRegExp)]
      ),
      email: new FormControl('', [Validators.pattern(this.userService.emailRegExp)]),
      password: new FormControl('', Validators.pattern(this.userService.passwordRegExp)),
      confirmPassword: new FormControl('')
    });
  }

  public register(): void {
    console.log('RegisterComponent:register');
    this.isError = false;
    let username = this.username!.value;
    let password = this.password!.value;
    let email = this.email!.value;

    this.regSubscription = this.authenticationService.register(username, password, email).subscribe(
      data => {
        window.sessionStorage.setItem('token', JSON.stringify(data));
        this.router.navigateByUrl('/verify');
      },

      err => {
        this.isError = true;
        this.regGroup.patchValue({password: '', confirmPassword: ''});
      }
    );
  }

  public ngOnDestroy(): void {
    if (this.regSubscription) {
      this.regSubscription.unsubscribe();
    }
    if (this.securitySubscription) {
      this.securitySubscription.unsubscribe();
    }
  }
}
