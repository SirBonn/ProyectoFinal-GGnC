import { Offer } from "./Offer";
import { Seeker } from "./Seeker";

export interface Application {
  idCode: number;
  seeker: Seeker;
  offer: Offer;
  seekerMessage: string;
  state: number;
}
