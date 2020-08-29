import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Reservation} from "../../shared/models/reservation.model";
import {Car} from "../../shared/models/car.model";

@Injectable()
export class ReservationService {

  private baseUrl = 'api';

  constructor(private http: HttpClient) { }

  saveReservation(reservation: Reservation): Observable<HttpResponse<Reservation>> {
    return this.http.post<Reservation>(this.baseUrl + '/reservation', reservation, {observe: 'response'});
  }

  deleteReservation(reservationId: number): Observable<HttpResponse<Reservation>> {
    return this.http.delete<Reservation>(this.baseUrl + '/reservation/' + reservationId, {observe: 'response'});
  }

  getMyReservations(): Observable<HttpResponse<Reservation[]>> {
    return this.http.get<Reservation[]>(this.baseUrl + '/myReservations', {observe: 'response'});
  }

}
