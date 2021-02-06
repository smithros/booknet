import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../models/user';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private readonly backendUrl;

  constructor(private http: HttpClient) {
    this.backendUrl = environment.apiBaseUrl;
  }

  public login(username: string, password: string) {
    let form = new FormData();
    form.append('login', username);
    form.append('password', password);

    return this.http.post<User>(`${this.backendUrl}/login`, form);
  }

  public register(login: string, password: string, email: string) {
    console.log('AuthenticationService:register');
    let form = new FormData();
    form.append('login', login);
    form.append('password', password);
    form.append('email', email);

    console.log(form);

    return this.http.post<User>(`${this.backendUrl}/register`, form);
  }
}
