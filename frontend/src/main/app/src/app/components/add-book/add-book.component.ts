import { Component, OnInit } from '@angular/core';
import {Book} from "../../models/book";
import {Genre} from "../../models/genre";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../services/api-service/api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Author} from "../../models/author";

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  public model: Book = new Book();
  public genres: Genre[] = [];
  public createdBook: FormGroup;
  public regExp = new RegExp(/^[A-Za-z0-9_]+$/);

  constructor(private apiService: ApiService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) {
  }

  getGenres() {
    this.apiService.getAllGenre().subscribe(
      genres => {
        this.genres = genres;
      });
  }

  get authors(): FormArray {
    return this.createdBook.get("authors") as FormArray
  }

  newAuthor(): FormGroup {
    return this.formBuilder.group({
      author: ['', Validators.pattern(this.regExp)]
    })
  }

  addAuthor() {
    this.authors.push(this.newAuthor());
  }

  removeAuthor(i: number) {
    this.authors.removeAt(i);
    if (i == 0) {
      this.authors.push(this.newAuthor());
    }
  }

  ngOnInit() {

    this.createdBook = this.formBuilder.group({
      header: ['', [Validators.required, Validators.pattern(this.regExp)]],
      overview: ['', [Validators.required, Validators.pattern(this.regExp)]],
      status: ['', [Validators.required, Validators.pattern(this.regExp)]],
      genre: ['', [Validators.required, Validators.pattern(this.regExp)]],
      authors: this.formBuilder.array([])
    });
    this.authors.push(this.newAuthor());
    this.getGenres();
  }

  createBook(): void {
    const book = this.createdBook.controls;
    this.model.title = book.header.value;
    this.model.text = book.overview.value;
    this.model.genres = book.genre.value;
    this.model.status = book.status.value;

    this.authors.getRawValue().forEach(author => {
      let newAuthors: Author = {id: 0, name: author['author'], books: null};
      this.model.authors.push(newAuthors);
    });

    this.apiService.createBook(this.model)
      .subscribe(res => {
          this.model.authors = [];
        },
        err => {
          this.router.navigateByUrl('/error');
        });
  }
}
