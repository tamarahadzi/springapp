import {Component, OnInit, SimpleChanges} from '@angular/core';
import {NgbDate, NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {Car} from "../../shared/models/car.model";
import {CarsService} from "../cars/cars.service";
import {Reservation} from "../../shared/models/reservation.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ReservationService} from "./reservation.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  hoveredDate: NgbDate | null = null;
  fromDate: NgbDate;
  toDate: NgbDate | null = null;
  cars: Car[];
  areCars: boolean = false;
  modalReference: NgbModalRef;
  carToReserve: Car;
  reservationForm: FormGroup;
  reservation: Reservation;
  dateDiff: number;
  fromDateString: string;
  toDateString: string;

  constructor(private fb: FormBuilder,
              private modalService: NgbModal,
              private carsService: CarsService,
              private reservationService: ReservationService) { }

  ngOnInit(): void {
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
      console.log("dates", this.fromDate, this.toDate);
      this.dateDiff = (new Date(this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day).getTime()
        -new Date(this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day).getTime())
        /(24*60*60*1000) + 1;
      console.log("dateDiff", this.dateDiff);
      this.fromDateString = this.fromDate.day+'-'+this.fromDate.month+'-'+this.fromDate.year;
      this.toDateString = this.toDate.day+'-'+this.toDate.month+'-'+this.toDate.year;
      console.log("datesString", this.fromDateString, this.toDateString);
      /*this.carsService.getAvailableCars(this.fromDateString, this.toDateString).subscribe(res => {
        this.cars = res.body;
        this.cars.forEach(car => {
          car.totalPrice = car.pricePerDay * this.dateDiff;
        });
        if(this.cars.length > 0) {
          this.areCars = true;
        }
      });*/
      this.carsService.getAllCars().subscribe(res => {
        this.cars = res.body;
        this.cars.forEach(car => {
          car.totalPrice = car.pricePerDay * this.dateDiff;
        });
        if(this.cars.length > 0) {
          this.areCars = true;
        }
      });
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return this.toDate && date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || (this.toDate && date.equals(this.toDate)) || this.isInside(date) || this.isHovered(date);
  }

  checkRange() {
    if(this.fromDate && this.toDate) {
      console.log("dates", this.fromDate, this.toDate);
    }
  }

  showReserveCarModal(car, modal) {
    this.carToReserve = car;
    this.reservationForm = this.fb.group({
      pickUpPlace: [''],
      dropOffPlace: ['']
    });
    this.modalReference = this.modalService.open(modal, {centered: true, size: 'lg'});
  }

  reserveCar() {
    this.reservation = new Reservation();
    this.reservation.carId = this.carToReserve.id;
    this.reservation.userId = JSON.parse(localStorage.getItem("loggedUser")).id;
    this.reservation.startDate = this.fromDateString;
    this.reservation.endDate = this.toDateString;
    this.reservation.startPlace = this.reservationForm.get('pickUpPlace').value;
    this.reservation.endPlace = this.reservationForm.get('dropOffPlace').value;
    this.reservation.car = null;
    console.log("this.reservation", this.reservation, JSON.parse(localStorage.getItem("loggedUser")));
    /*this.reservationService.saveReservation(this.reservation).subscribe(res => {
      this.modalReference.close();
    });*/
  }

}
