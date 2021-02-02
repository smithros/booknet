import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  public currentUser: Observable<User>;
  private currentUserSubject!: BehaviorSubject<User>;

  constructor() {
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public getUser(): User {
    return this.currentUserSubject.getValue();
  }

  public setUser(value: any) {
    this.currentUserSubject.next(value);
  }
}
