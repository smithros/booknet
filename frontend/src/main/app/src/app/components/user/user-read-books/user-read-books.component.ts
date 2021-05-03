import { Component, OnInit } from '@angular/core';
import {BookListComponent} from "../../book-list/book-list.component";
import {ApiService} from "../../../services/api-service/api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-user-read-books',
  templateUrl: './user-read-books.component.html',
  styleUrls: ['./user-read-books.component.css']
})
export class UserReadBooksComponent extends BookListComponent implements OnInit {

  private searchTitle: string;

  constructor(public apiService: ApiService,
              public route: ActivatedRoute,
              public router: Router,
              public storage: StorageService) {
    super(apiService, route, router, storage);
  }

  ngOnInit() {
    super.getAllReadBooks();
    super.getAllAuthor();
    super.getAllGenre();
  }
}
