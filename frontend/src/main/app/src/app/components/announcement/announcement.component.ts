import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {ApiService} from "../../services/api-service/api.service";
import {User} from "../../models/user";
import {Announcement} from "../../models/announcement";

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})

export class AnnouncementComponent implements OnInit {
  public model: Announcement = {
    id: 0,
    description: '',
    date: new Date(),
    bookId: 0,
    ownerId: 0,
    adminId: 0,
    status: false
  };
  public currentUser: User;
  public bookId: number;
  public currentDate: Date;
  public formatted_date: string;

  constructor(private http: HttpClient,
              private route: ActivatedRoute,
              private storage: StorageService,
              private apiService: ApiService) {
  }

  ngOnInit() {
    this.currentDate = new Date();
    this.formatted_date = this.currentDate.getFullYear()
      + "-" + (this.currentDate.getMonth() + 1)
      + "-" + this.currentDate.getDate()
    this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
    this.currentUser = this.storage.getUser();
    this.currentDate = new Date()
  }

  compareDate(date: Date): boolean {
    return date > this.currentDate;
  }

  createAnnouncement(): void {
    this.model.bookId = this.bookId;
    //if (this.currentUser.userRole == 'moderator') {
    this.model.status = true;
    this.model.adminId = this.currentUser.id;
    //}
    this.model.ownerId = this.currentUser.id;
    this.apiService.createAnnouncement(this.model).subscribe(
      err => {
        alert(JSON.parse(JSON.stringify(err)).message);
      }
    );
  }
}
