import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {LoginService} from "./login/login.service";
import {HttpClientModule} from "@angular/common/http";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './navbar/navbar.component';
import { CarsComponent } from './navbar/cars/cars.component';
import { UsersComponent } from './navbar/users/users.component';
import {UsersService} from "./navbar/users/users.service";
import {CarsService} from "./navbar/cars/cars.service";
import { ReservationComponent } from './navbar/reservation/reservation.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    CarsComponent,
    UsersComponent,
    ReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    LoginService,
    UsersService,
    CarsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
