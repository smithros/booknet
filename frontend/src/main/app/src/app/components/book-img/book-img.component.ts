import {Component, Input, OnInit} from '@angular/core';
import {ApiService} from "../../services/api-service/api.service";
import {Book} from "../../models/book";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-book-img',
  templateUrl: './book-img.component.html',
  styleUrls: ['./book-img.component.css']
})
export class BookImgComponent implements OnInit {

  @Input() book: Book;

  constructor(private apiService: ApiService,
              private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    if (this.book != null) {
      this.getImgByBook();
    }
  }

  getImgByBook() {
    this.apiService.getImageByBook(this.book).subscribe(
      res => {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
          this.book.photoURL = reader.result
        }, false);
        if (res) {
          reader.readAsDataURL(res);
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  getSantizeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
}
