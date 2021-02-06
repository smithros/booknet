import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../../models/user';
import {environment} from '../../../environments/environment';
import {StorageService} from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private readonly backendUrl;

  constructor(private http: HttpClient,
              private storageService: StorageService) {
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

  public logout(): void {
    //window.sessionStorage.removeItem('token');
    window.sessionStorage.removeItem('user');
    this.storageService.setUser(null);
  }
}
