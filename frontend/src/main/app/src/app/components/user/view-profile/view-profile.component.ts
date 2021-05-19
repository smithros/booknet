import {Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {User} from "../../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../../services/user-service/user.service";
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})

export class ViewProfileComponent implements OnInit, OnChanges, OnDestroy {
  @Input() public user: User;
  public currentUser: User;
  public isAbleToAddToFriend: boolean;
  public subscription: Subscription;

  constructor(private userService: UserService,
              private router: Router,
              private storageService: StorageService) {
    this.currentUser = this.storageService.getUser();
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.user = changes.user.currentValue;
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
