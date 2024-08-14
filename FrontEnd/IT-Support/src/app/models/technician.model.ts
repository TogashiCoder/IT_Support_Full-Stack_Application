export interface Technician {
  id:number
  username: string
  email: string
  password: string
  role: string
}

export interface TechnicianCreate {
  username: string;
  email: string;
  password: string;
  role: string;
}
