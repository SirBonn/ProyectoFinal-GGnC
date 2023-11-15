import { Category } from "./Category";
import { Employer } from "./Employer";
import { User } from "./User";

export interface Offer {
  idCode: number;
  offerName: string;
  offerDesc: string;
  employer: Employer;
  category: Category;
  offerState: number;
  publicationDate: string;
  expireDate: string;
  payment: number;
  plataformPayment: number;
  modality: number;
  direction: string;
  details: string;
}
