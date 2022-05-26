import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../models/user';
import {environment} from '../../../environments/environment';
import {userSearch} from "../../models/userSearch";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl: string = environment.apiBaseUrl;

  public inputRegExp = new RegExp(/^(?=\D)+\w*$/);
  public passwordRegExp = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$_!%*#?&])[A-Za-z\d@$!%*_#?&]{8,}$/);
  public emailRegExp = new RegExp(/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/);

  constructor(private http: HttpClient) {

  }

  public getUser(id: string) {
    const url = `${this.usersUrl}/user/${id}`;
    return this.http.get<User>(url);
  }

  public deactivateAccount(id: string) {
    const url = `${this.usersUrl}/user/${id}/deactivate`;
    return this.http.get(url);
  }

  updateProfile(user: User) {
    let url = `${this.usersUrl}/user/update`;
    let form = new FormData();
    form.append('login', user.name);
    form.append('newPassword', user.userPassword);
    form.append('newEmail', user.email);
    return this.http.post<User>(url, form);
  }

  getUserSettings(userId:number):Observable<User> {
    let params = new HttpParams().append(
      'userId', userId.toString()
    );

    let url = `${this.usersUrl}/getSettings`;
    return this.http.post<User>(url, params);
  }

  searchByUsername(username: string) {
    let url = `${this.usersUrl}/user/search/${username}`;
    return this.http.get<userSearch[]>(url);
  }

  public findAll(): Observable<userSearch[]> {
    return this.http.get<userSearch[]>(`${this.usersUrl}/user/get/all`);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }
}
