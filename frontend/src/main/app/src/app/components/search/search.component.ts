import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user-service/user.service";
import {userSearch} from "../../models/userSearch";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

export class SearchComponent implements OnInit, OnDestroy {
  public search: string = '';
  public userModel: userSearch[] = [];

  public routSubscription: Subscription;
  public searchSubscription: Subscription;
  public getAllSubscription: Subscription;

  constructor(private route: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit() {
    this.routSubscription = this.route.queryParams.subscribe(
      param => {
        this.userModel = [];
        if (param.search.length > 0) {
          this.search = param.search;
          this.searchSubscription = this.userService.searchByUsername(this.search).subscribe(
            res => {
              this.userModel = res;
              console.log(this.userModel)
            },
            () => {
              this.userModel = null;
            });
        } else {
          this.getAllSubscription = this.userService.findAll().subscribe(
            res => {
              this.userModel = res;
            },
            () => {
              this.userModel = null;
            }
          );
        }
      });
  }

  ngOnDestroy(): void {
    this.routSubscription.unsubscribe();
    if (this.searchSubscription) {
      this.searchSubscription.unsubscribe();
    }
    if (this.getAllSubscription) {
      this.getAllSubscription.unsubscribe();
    }
  }
}
