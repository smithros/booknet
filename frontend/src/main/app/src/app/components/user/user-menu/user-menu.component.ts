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
        this.isAllowedToAdd = this.currentUser.role == 'ADMIN';
        this.isAllowedToChange = this.currentUser.role == 'USER';
        this.isAllowedToPublish = this.currentUser.role == 'MODERATOR';
      } else {
        this.isAllowedToAdd = this.isAllowedToPublish = false;
        this.isAllowedToDeactivate = this.user.role != 'USER'
          && (this.currentUser.role == 'ADMIN' || this.user.role == 'MODERATOR');
      }
    }
  }

  public open(tab: string) {
    this.isOpen = tab;
    this.toOpen.emit(tab);
  }
}
