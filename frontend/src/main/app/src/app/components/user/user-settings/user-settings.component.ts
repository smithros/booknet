import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css']
})

export class UserSettingsComponent implements OnInit {
  private subscription: Subscription;
  private updateSubscription: Subscription;
  public isError: boolean = false;
  private siteUrl: string = environment.apiBaseUrl;

  constructor(private http: HttpClient, private storage: StorageService) {
  }

  public settingsData = [
    {value: true, name: 'notify about friends announcements'},
    {value: true, name: 'notify about friends actions with books'},
    {value: true, name: 'notify about friends achievements'},
    {value: true, name: 'notify about new friends'},
    {value: true, name: 'notify about friends reviews'},
  ];

  ngOnInit() {
    let form = new FormData();
    form.append('userId', String(this.storage.getUser().id));
    const params = new HttpParams()
      .set('userId', String(this.storage.getUser().id));

/*    this.subscription = this.http.get<UserSettings>(this.siteUrl + '/getSettings', {params: params}).subscribe(
      settings => {
        this.settingsData[0].value = settings.notifyAboutAnnouncements;
        this.settingsData[1].value = settings.bookNotification;
        this.settingsData[2].value = settings.achievements;
        this.settingsData[3].value = settings.notifyAboutNewFriends;
        this.settingsData[4].value = settings.subscribeOnFriendReview;
      },
      error => {
        this.isError = true;
      }
    );*/
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    if (this.updateSubscription) {
      this.updateSubscription.unsubscribe();
    }
  }

  onToggle(name: string, value: boolean) {
    let reverseValue: boolean = true;
    if (value === true) {
      reverseValue = false;
    }
    this.settingsData.filter(data => data.name === name).map(dt => dt.value = reverseValue);
  }

  submitSettings() {
    let form = new FormData();
    form.append('subscribeOnFriends', String(this.settingsData[0].value));
    form.append('bookNotification', String(this.settingsData[1].value));
    form.append('achivements', String(this.settingsData[2].value));
    form.append('notifyAboutNewFriends', String(this.settingsData[3].value));
    form.append('subscribeOnFriendReview', String(this.settingsData[4].value));
    form.append('userId', String(this.storage.getUser().id));

    this.updateSubscription = this.http.post(this.siteUrl + '/updateSettings' , form).subscribe(
      data => {
      },
      error => {
        this.isError = true;
      }
    );
  }


}
