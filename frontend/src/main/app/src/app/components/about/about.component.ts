import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../services/storage/storage.service";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  username: string

  constructor(private storage: StorageService) { }

  ngOnInit() {
    this.username = this.storage.getUser().name;
  }
}
