import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../services/api-service/api.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  public books: Book[] = [];
  public url: string = environment.apiBaseUrl;

  constructor(private http: HttpClient, private api: ApiService) {
    this.api.getBooks().toPromise().then(
      books => {
        this.books = books;
      }
    );
  }

  ngOnInit(): void {
  }
}
