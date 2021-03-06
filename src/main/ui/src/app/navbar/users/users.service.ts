import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../../shared/models/user.model";
import {Car} from "../../shared/models/car.model";

@Injectable()
export class UsersService {

  private baseUrl = 'api';

  constructor(private http: HttpClient) { }

  saveUser(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.baseUrl + '/users', user, {observe: 'response'});
  }

  updateUser(user: User): Observable<HttpResponse<User>> {
    return this.http.put<User>(this.baseUrl + '/users', user, {observe: 'response'});
  }

  deleteUser(userId: number): Observable<HttpResponse<User>> {
    return this.http.delete<User>(this.baseUrl + '/users/' + userId, {observe: 'response'});
  }

  getAllUsers(): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.baseUrl + '/users', {observe: 'response'});
  }

}
