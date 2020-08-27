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
  }

  logout() {
    this.router.navigate(['/login']);
  }
}
