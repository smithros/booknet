import {Component, Inject, OnInit} from '@angular/core';
import {Announcement} from "../../models/announcement";
import {Book} from "../../models/book";
import {HttpClient} from "@angular/common/http";
import {ApiService} from "../../services/api-service/api.service";

@Component({
  selector: 'app-announcement-list',
  templateUrl: './announcement-list.component.html',
  styleUrls: ['./announcement-list.component.css']
})
export class AnnouncementListComponent implements OnInit {
  public announcements: Announcement[] = [];
  public book: Book;

  constructor(private http: HttpClient,
              private apiService: ApiService) {
  }

  ngOnInit() {
    this.getAllAnnouncement();
  }

  public getAllAnnouncement() {
    this.apiService.getAnnouncements().subscribe(
      res => {
        console.log(res)
        this.announcements = res;
      },
      err => {
        alert("Error in getting all announcements");
      }
    )
  }
}
