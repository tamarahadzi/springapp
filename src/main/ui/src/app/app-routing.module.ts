import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {MyReservationsComponent} from "./navbar/my-reservations/my-reservations.component";

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "navbar", component: NavbarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
