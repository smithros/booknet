/*
 * MIT License
 *
 * Copyright (c) 2021 Koval Rostyslav
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {StorageService} from '../storage/storage.service';
import {User} from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private readonly backendUrl;

  constructor(private http: HttpClient,
              private storageService: StorageService) {
    this.backendUrl = 'http://localhost:8080';
  }

  public login(username: string, password: string) {
    let form = new FormData();
    form.append('login', username);
    form.append('password', password);

    return this.http.post<User>(`${this.backendUrl}/login`, form);
  }

  public register(login: string, password: string, email: string) {
    let form = new FormData();
    form.append('login', login);
    form.append('password', password);
    form.append('email', email);

    return this.http.post<User>(`${this.backendUrl}/register`, form);
  }

  public logout() {
    window.sessionStorage.removeItem('user');
    this.storageService.setUser(null);
  }
}
