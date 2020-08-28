import {Component, OnInit, SimpleChanges} from '@angular/core';
import {NgbDate, NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {Car} from "../../shared/models/car.model";
import {CarsService} from "../cars/cars.service";

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

  constructor(private modalService: NgbModal,
              private carsService: CarsService) { }

  ngOnInit(): void {
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
      console.log("dates", this.fromDate, this.toDate);
      /*this.carsService.getAvailableCars(this.fromDate, this.toDate).subscribe(res => {
        this.cars = res.body;
        if(this.cars.length > 0) {
          this.areCars = true;
        }
      });*/
      this.carsService.getAllCars().subscribe(res => {
        this.cars = res.body;
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
    this.modalService.open(modal, {centered: true, size: 'lg'});
  }

  reserveCar() {

  }

}
