import { Component, OnInit } from '@angular/core';
import {Announcement} from "../../models/announcement";
import {User} from "../../models/user";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api-service/api.service";
import {StorageService} from "../../services/storage/storage.service";

@Component({
  selector: 'app-announcement-propose',
  templateUrl: './announcement-propose.component.html',
  styleUrls: ['../announcement-list/announcement-list.component.css']
})

export class AnnouncementProposeComponent implements OnInit {
  public announcements: Announcement[] = [];
  public currentUser: User;

  constructor(private http: HttpClient,
              private route: ActivatedRoute,
              private storage: StorageService,
              private apiService: ApiService) {
  }

  ngOnInit() {
    this.getAllAnnouncement();
    this.currentUser = this.storage.getUser();
  }

  public getAllAnnouncement() {
    this.apiService.getAnnouncementsUnPublish().subscribe(
      res => {
        this.announcements = res;
      },
      err => {
        alert("Error in getting all announcements");
      }
    )
  }

  public publishAnnouncement(announcement: Announcement) {
    this.apiService.publishAnnouncement(announcement).subscribe(
      res => {
      }
    );
    this.getAllAnnouncement();
  }
}
