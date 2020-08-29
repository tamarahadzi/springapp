import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../shared/models/user.model";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  active : number = 1;
  loggedUser: User;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.loggedUser = JSON.parse(localStorage.getItem("loggedUser"));
    if(this.loggedUser.role.name=='ADMIN') {
      this.active = 1;
    } else {
      this.active = 3;
    }
  }

  logout() {
    localStorage.removeItem("loggedUser");
    this.router.navigate(['/login']);
  }

  changeActiveTab(event) {
    console.log("activeNUmber", event);
    this.active = event;
  }
}
