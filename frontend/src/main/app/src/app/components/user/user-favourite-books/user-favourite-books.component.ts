import { Component, OnInit } from '@angular/core';
import {BookListComponent} from "../../book-list/book-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../../../services/api-service/api.service";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-user-favourite-books',
  templateUrl: './user-favourite-books.component.html',
  styleUrls: ['./user-favourite-books.component.css']
})
export class UserFavouriteBooksComponent extends BookListComponent implements OnInit {

  private searchTitle: string;

  constructor(public apiService: ApiService,
              public route: ActivatedRoute,
              public router: Router,
              public storage: StorageService) {
    super(apiService, route, router, storage);
  }

  ngOnInit() {
    super.getAllFavouriteBooks();
    super.getAllAuthor();
    super.getAllGenre();
  }
}
