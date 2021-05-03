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
  public createdBook: FormGroup;
  public regExp = new RegExp(/^[A-Za-z0-9_]+$/);

  constructor(private apiService: ApiService,
              private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) {
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

  get genres(): FormArray {
    return this.createdBook.get("genres") as FormArray
  }

  newGenre(): FormGroup {
    return this.formBuilder.group({
      genre: ['', Validators.pattern(this.regExp)]
    })
  }

  addGenre() {
    this.genres.push(this.newGenre());
  }

  removeGenre(i: number) {
    this.genres.removeAt(i);
    if (i == 0) {
      this.genres.push(this.newGenre());
    }
  }

  ngOnInit() {
    this.createdBook = this.formBuilder.group({
      title: ['', [Validators.required, Validators.pattern(this.regExp)]],
      text: ['', [Validators.required, Validators.pattern(this.regExp)]],
      status: ['', [Validators.required, Validators.pattern(this.regExp)]],
      genres: this.formBuilder.array([]),
      authors: this.formBuilder.array([])
    });
    this.authors.push(this.newAuthor());
    this.genres.push(this.newGenre());
  }

  createBook(): void {
    const book = this.createdBook.controls;
    this.model.title = book.title.value;
    this.model.text = book.text.value;
    this.model.status = book.status.value;
    this.model.authors = [];
    this.model.genres = [];

    this.authors.getRawValue().forEach(author => {
      let newAuthor: Author = {id: 0, name: author['author'], books: null};
      this.model.authors.push(newAuthor);
    });

    this.genres.getRawValue().forEach(genre => {
      let newGenre: Genre = {id: 0, desc: genre['genre'], books: null};
      this.model.genres.push(newGenre);
    });

    this.apiService.createBook(this.model)
      .subscribe(res => {
          this.model.authors = [];
          this.model.genres = [];
        },
        err => {
          this.router.navigateByUrl('/error');
        });
  }

  back() {
    this.router.navigateByUrl('/books');
  }
}
