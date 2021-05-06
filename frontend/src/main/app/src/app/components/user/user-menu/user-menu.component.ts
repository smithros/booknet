import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges
} from '@angular/core';
import {User} from "../../../models/user";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})

export class UserMenuComponent implements OnInit, OnChanges {
  @Input() public user: User;
  public currentUser: User;
  @Output() public toOpen = new EventEmitter<string>();
  public isOpen: string;
  public isAllowedToChange: boolean;
  public isAllowedToAdd: boolean;
  public isAllowedToDeactivate: boolean;
  public isAllowedToPublish: boolean;

  constructor(private storageService: StorageService) {
    this.currentUser = this.storageService.getUser()
  }

  public ngOnInit() {
  }

  public ngOnChanges(changes: SimpleChanges): void {
    this.open('View');
    this.user = changes.user.currentValue;

    if (this.user.id) {
      if (this.user.id == this.currentUser.id) {
        this.isAllowedToDeactivate = false;
        this.isAllowedToAdd = this.currentUser.userRole == 'super' || this.currentUser.userRole == 'admin';
        this.isAllowedToChange = this.currentUser.userRole == 'user';
        this.isAllowedToPublish = this.currentUser.userRole == 'moderator';
      } else {
        this.isAllowedToAdd = this.isAllowedToPublish = false;
        this.isAllowedToDeactivate = this.isAllowedToChange = this.currentUser.userRole == 'super'
          && this.user.userRole != 'user'
          || (this.currentUser.userRole == 'admin' && this.user.userRole == 'moderator');
      }
    }
  }

  public open(tab: string) {
    this.isOpen = tab;
    this.toOpen.emit(tab);
  }
}
