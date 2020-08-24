import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../shared/models/user.model";

@Injectable()
export class LoginService {

  private baseUrl = 'api';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<HttpResponse<User>> {
    const formdata: FormData = new FormData();
    formdata.append('email', email);
    formdata.append('password', password);
    return this.http.post<User>(this.baseUrl + '/login', formdata, {observe: 'response'});
  }

  signUp(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.baseUrl + '/signUp', user, {observe: 'response'});
  }

}
