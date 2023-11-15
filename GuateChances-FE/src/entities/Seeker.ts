export interface Seeker{
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
  telephone: number[]
}
