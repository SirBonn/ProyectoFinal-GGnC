import { Card } from './Card';

export interface Employer{
  idCode: string,
  forename: string,
  direction?: string,
  username: string,
  password?: string,
  noCUI?: number,
  email: string,
  birthdate: Date,
  usertype: number,
  isActive: number,
  card: Card,
  vision: string,
  mision: string,
  telephone: number[]
}
