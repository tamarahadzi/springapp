import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../../shared/models/user.model";
import {Car} from "../../shared/models/car.model";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {CarsService} from "./cars.service";

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  carForm: FormGroup;
  modalReference: NgbModalRef;
  car: Car;
  cars: Car[];

  constructor(private fb: FormBuilder,
              private modalService: NgbModal,
              private carsService: CarsService) { }

  ngOnInit(): void {
    this.getAllCars();
  }

  getAllCars() {
    this.carsService.getAllCars().subscribe(res => {
      console.info("res", res.body);
      this.cars = res.body;
    })
  }

  showSaveCarModal(modal) {
    this.carForm = this.fb.group({
      brand: [],
      model: [],
      gearbox: [''],
      year: [],
      pricePerDay:[],
      airCondition: [],
      doors: [],
      seats: [],
      smallBag: [],
      largeBag: []
    });
    this.modalReference = this.modalService.open(modal, { centered: true, size: 'lg' });
  }

  saveCar() {
    this.car = new Car();
    this.car.brand = this.carForm.get('brand').value;
    this.car.model = this.carForm.get('model').value;
    this.car.gearbox = this.carForm.get('gearbox').value;
    this.car.year = this.carForm.get('year').value;
    this.car.pricePerDay = this.carForm.get('pricePerDay').value;
    this.car.airCondition = this.carForm.get('airCondition').value;
    this.car.doors = this.carForm.get('doors').value;
    this.car.seats = this.carForm.get('seats').value;
    this.car.smallBag = this.carForm.get('smallBag').value;
    this.car.largeBag = this.carForm.get('largeBag').value;
    console.log("this.car", this.car);
    this.carsService.saveCar(this.car).subscribe(res => {
      this.getAllCars();
      this.modalReference.close();
    });
  }

  deleteCar(carId: number) {
    this.carsService.deleteCar(carId).subscribe(res => {
      this.getAllCars();
    })
  }

}
