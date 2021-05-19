import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../../services/api-service/api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {BookListComponent} from "../../book-list/book-list.component";

@Component({
  selector: 'app-user-books',
  templateUrl: './user-books.component.html',
  styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent extends BookListComponent implements OnInit {

  public searchTitle: string;

  constructor(public apiService: ApiService,
              public route: ActivatedRoute,
              public router: Router,
              public storage: StorageService) {
    super(apiService, route, router, storage);
  }

  ngOnInit() {
    super.getUsersBookList();
    super.getAllAuthor();
    super.getAllGenre();
    super.checkHistoryFilter();
  }
}

