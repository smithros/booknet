import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DomSanitizer} from "@angular/platform-browser";
import {StorageService} from "../../services/storage/storage.service";
import {FormGroup} from "@angular/forms";
import {Book} from "../../models/book";
import {environment} from "../../../environments/environment";
import {User} from "../../models/user";
import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-book-file',
  templateUrl: './book-file.component.html',
  styleUrls: ['./book-file.component.css']
})

export class BookFileComponent implements OnInit {
  public form: FormGroup;
  @Input() public book: Book;
  public fileUploadVisible: boolean = false;
  public fileExtensions = ['.txt', '.pdf'];
  public imgExtensions = ['.png'];
  public fileToUpload: File;
  public receivedImageData: any;
  public base64Data: any;
  public convertedImage: any;
  public image: any;
  public file: any;
  public blob: any;
  public downloadDisable: boolean = false;
  private siteUrl: string = environment.apiBaseUrl;
  public maxSize: boolean = false;

  constructor(private http: HttpClient,
              private sanitizer: DomSanitizer,
              private storage: StorageService) {
  }

  ngOnInit() {
    let user: User = this.storage.getUser();
    if (user != null) {
      if (user.role == 'MODERATOR') {
        this.fileUploadVisible = true;
      }
    }
    this.uploadFile()
  }

  uploadFile() {
    const url = `${this.siteUrl}` + `/book/bookFile`;
    return this.http.post(url, this.book, {responseType: 'blob' as 'text'}).subscribe(
      (res) => {
        this.blob = new Blob([res], {type: 'application/pdf'});
        if (this.blob.size != 0) {
          this.downloadDisable = true;
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  postFile(event) {
    this.fileToUpload = event.target.files[0];
    let formData = new FormData();
    let name = this.fileToUpload.name;
    let index = name.lastIndexOf(".");
    let extensions = name.substring(index, name.length);
    console.log(event.target.files[0].size);
    if (event.target.files[0].size / 1000 > 2863474) {
      this.maxSize = true;
    } else {
      if (this.imgExtensions.indexOf(extensions) != -1) {
        const url = `${this.siteUrl}` + `/book/addImage`;
        formData.append('img', this.fileToUpload);
        formData.append('bookId', this.book.id.toString());
        this.http.post(url, formData).subscribe(res => {
            this.receivedImageData = res;
            this.base64Data = this.receivedImageData.pic;
            this.convertedImage = 'data:image/png;base64,' + this.base64Data;
          },
          err => console.log(err)
        );

      }
      if (this.fileExtensions.indexOf(extensions) != -1) {
        const url = `${this.siteUrl}` + `/book/addFile`;
        formData.append('file', this.fileToUpload);
        formData.append('bookId', this.book.id.toString());
        this.http.post(url, formData).subscribe((file) => {
          console.log(file);
        });
      }
    }
  }

  downloadPDF(): any {
    this.book.fileURL = URL.createObjectURL(this.blob);
    this.file = new File([this.blob], this.book.title, {type: this.blob.type});
    FileSaver.saveAs(this.file);
  }
}
