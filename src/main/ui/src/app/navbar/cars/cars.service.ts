import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";
import {Car} from "../../shared/models/car.model";

@Injectable()
export class CarsService {

  private baseUrl = 'api';

  constructor(private http: HttpClient) { }

  saveCar(car: Car): Observable<HttpResponse<Car>> {
    return this.http.post<Car>(this.baseUrl + '/cars', car, {observe: 'response'});
  }

  updateCar(car: Car): Observable<HttpResponse<Car>> {
    return this.http.put<Car>(this.baseUrl + '/cars', car, {observe: 'response'});
  }

  deleteCar(carId: number): Observable<HttpResponse<Car>> {
    return this.http.delete<Car>(this.baseUrl + '/cars/' + carId, {observe: 'response'});
  }

  getAllCars(): Observable<HttpResponse<Car[]>> {
    return this.http.get<Car[]>(this.baseUrl + '/cars', {observe: 'response'});
  }

  getAvailableCars(startDate: string, endDate: string): Observable<HttpResponse<Car[]>> {
    const formdata: FormData = new FormData();
    formdata.append('startDate', startDate);
    formdata.append('endDate', endDate);
    return this.http.post<Car[]>(this.baseUrl + '/availableCars',formdata, {observe: 'response'});
  }

}
