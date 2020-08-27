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
  carId: number;
  saveOrUpdateCar: boolean;

  constructor(private fb: FormBuilder,
              private modalService: NgbModal,
              private carsService: CarsService) { }

  ngOnInit(): void {
    this.getAllCars();
  }

  getAllCars() {
    this.carsService.getAllCars().subscribe(res => {
      console.info("allCars", res.body);
      this.cars = res.body;
    })
  }

  showSaveCarModal(saveOrUpdate, car, modal) {
    this.saveOrUpdateCar = saveOrUpdate;
    if(!saveOrUpdate) {
      this.carForm = this.fb.group({
        brand: [],
        model: [],
        gearbox: [''],
        year: [],
        pricePerDay: [],
        airCondition: [],
        doors: [],
        seats: [],
        smallBag: [],
        largeBag: []
      });
    } else {
      this.carId = car.id;
      this.carForm = this.fb.group({
        brand: [car.brand],
        model: [car.model],
        gearbox: [car.gearbox],
        year: [car.year],
        pricePerDay: [car.pricePerDay],
        airCondition: [car.airCondition],
        doors: [car.doors],
        seats: [car.seats],
        smallBag: [car.smallBag],
        largeBag: [car.largeBag]
      });
    }
    this.modalReference = this.modalService.open(modal, {centered: true, size: 'lg'});
  }

  saveCar() {
    this.car = new Car();
    if(this.saveOrUpdateCar) {
      this.car.id = this.carId;
    }
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
    if(!this.saveOrUpdateCar) {
      this.carsService.saveCar(this.car).subscribe(res => {
        this.getAllCars();
        this.modalReference.close();
      });
    } else {
      this.carsService.updateCar(this.car).subscribe(res => {
        this.getAllCars();
        this.modalReference.close();
      });
    }

  }

  showDeleteCarModal(carId, modal) {
    this.carId = carId;
    this.modalReference = this.modalService.open(modal, {centered: true});
  }

  deleteCar() {
    this.carsService.deleteCar(this.carId).subscribe(res => {
      this.getAllCars();
      this.modalReference.close();
    })
  }

}
