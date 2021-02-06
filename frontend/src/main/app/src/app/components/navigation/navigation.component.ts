import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {User} from '../../models/user';
import {StorageService} from '../../services/storage/storage.service';
import {AuthenticationService} from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  public searchString: string = '';
  public subscription: Subscription;
  public user: User;

  constructor(private storageService: StorageService,
              private authenticationService: AuthenticationService
  ) {
    this.subscription = this.storageService.currentUser.subscribe(user => {
      this.user = user;
    });
  }

  public ngOnInit() {
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public logout(): void {
    this.authenticationService.logout();
  }

  public clear() {
    this.searchString = '';
  }

}
