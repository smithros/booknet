import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {User} from "../../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../../services/user-service/user.service";
import {ActivatedRoute} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})

export class EditProfileComponent implements OnInit, OnDestroy {
  public repeatPassword: string = '';
  public editStatus: string;

  @Input() public user: User;
  @Output() public userChange = new EventEmitter();

  public updatedUser: User = new User();
  private subscription: Subscription;

  constructor(private userService: UserService) {
  }

  public ngOnInit() {
    Object.assign(this.updatedUser, this.user);
  }

  public update(): void {
    this.editStatus = '';
    this.subscription = this.userService.updateProfile(this.updatedUser)
      .subscribe(
        user => {
          this.editStatus = 'success';
          this.repeatPassword = '';
          this.userChange.emit(user);
        },
        error => {
          this.editStatus = 'error';
          Object.assign(this.updatedUser, this.user);
          this.repeatPassword = '';
        });
  }

  public ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
