import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../../models/user';
import {BookFilter} from "../../models/bookFilter";

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  public currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;
  public currentFilter: Observable<BookFilter>;
  private currentFilterSubject: BehaviorSubject<BookFilter>;

  constructor() {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(sessionStorage.getItem('user'))
    );
    this.currentUser = this.currentUserSubject.asObservable();

    this.currentFilterSubject = new BehaviorSubject<BookFilter>(
      {header: "", author: [], genre: []}
      );
    this.currentFilter = this.currentFilterSubject.asObservable();
  }

  public getUser(): User {
    return this.currentUserSubject.getValue();
  }

  public setUser(value: any): void {
    this.currentUserSubject.next(value);
  }

  setFilter(value: BookFilter) {
    this.currentFilterSubject.next(value);
  }

  getFilter() {
    return this.currentFilterSubject.getValue();
  }
}
