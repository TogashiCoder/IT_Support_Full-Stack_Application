import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Technician } from '../models/technician.model';


@Injectable({
  providedIn: 'root'
})
export class TechnicianService {

  private apiUrl ="http://localhost:8080/api/technicians";

  constructor(private http:HttpClient) { }




// create New Technician
createNewTechnician(technician: Technician): Observable<Technician> {
  return this.http.post<Technician>(this.apiUrl, technician);
}


//get all Technicians
getTechnicians():Observable<Technician[]>{

    return this.http.get<Technician[]>(this.apiUrl)
}


//get specific Technician
getTechnicianById(id:Number):Observable<Technician>
{
  const url = `${this.apiUrl}/${id}`
  return this.http.get<Technician>(url);
}


//update Technician
updateTechnician(id:number,user:Technician):Observable<Technician>
{
  const url = `${this.apiUrl}/${id}`
  return this.http.put<Technician>(url,user);
}


// delete Technician
deleteTechnician(id: Number): Observable<void> {
const url = `${this.apiUrl}/${id}`;
return this.http.delete<void>(url);
}




}








