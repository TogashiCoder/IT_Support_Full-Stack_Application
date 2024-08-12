import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fault } from '../models/fault.model';



@Injectable({
  providedIn: 'root'
})
export class FaultService {

  private apiUrl ="http://localhost:8080/api/faults/admin";

  constructor(private http:HttpClient) { }

 // create New Fault
 createNewFault(fault: Fault): Observable<Fault> {
  return this.http.post<Fault>(this.apiUrl, fault);
}


//get all Faults
getallFaults():Observable<Fault[]>{

    return this.http.get<Fault[]>(this.apiUrl)
}


//get specific Fault
getFaultById(id:Number):Observable<Fault>
{
  const url = `${this.apiUrl}/${id}`
  return this.http.get<Fault>(url);
}


//update Fault
updateFault(id:number,user:Fault):Observable<Fault>
{
  const url = `${this.apiUrl}/${id}`
  return this.http.put<Fault>(url,user);
}


// delete Fault
deleteFault(id: Number): Observable<void> {
const url = `${this.apiUrl}/${id}`;
return this.http.delete<void>(url);
}


}
