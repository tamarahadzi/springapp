import { Component, OnInit } from '@angular/core';
import {Reservation} from "../../shared/models/reservation.model";
import {ReservationService} from "../reservation/reservation.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {

  myReservations: Reservation[];
  reservation: Reservation;
  reservationId: number;
  modalReference: NgbModalRef;

  constructor(private modalService: NgbModal,
              private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.getMyReservations();
  }

  getMyReservations() {
    this.reservationService.getMyReservations().subscribe(res => {
      this.myReservations = res.body;
    });
  }

  showReservationDetailsModal(reservation, modal) {
    this.reservation = reservation;
    this.modalReference = this.modalService.open(modal, {centered: true, size: 'lg'});
  }

  showDeleteReservationModal(reservationId, modal) {
    this.reservationId = reservationId;
    this.modalReference = this.modalService.open(modal, {centered: true});
  }

  deleteReservation() {
    this.reservationService.deleteReservation(this.reservationId).subscribe(res => {
      this.getMyReservations();
      this.modalReference.close();
    })
  }
}
