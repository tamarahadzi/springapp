import {Car} from "./car.model";

export class Reservation {
  public id: number;
  public userId: number;
  public carId: number;
  public startDate: string;
  public endDate: string;
  public startPlace: string;
  public endPlace: string;
  public price: number;
  public car: Car;
}
