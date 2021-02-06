import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  public books: Book[] = [];
  public url: string = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
    this.getBooks().toPromise().then(
      books => {
        this.books = books;
      }
    );
  }

  ngOnInit(): void {
  }

  public getBooks(): Observable<Book[]> {
    const url = `${this.url}/book/all`;
    return this.http.get<Book[]>(url);
  }
}
