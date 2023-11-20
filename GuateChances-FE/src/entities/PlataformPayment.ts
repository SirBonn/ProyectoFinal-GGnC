import { User } from "./User";

export interface PlataformPayment {

  idCode: number,
  date: Date,
  user: User,
  payment: number

}
