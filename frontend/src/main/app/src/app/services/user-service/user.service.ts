import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../models/user';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl: string = environment.apiBaseUrl;

  constructor(private http: HttpClient) {

  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.usersUrl}/user/get/all`);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }
}
