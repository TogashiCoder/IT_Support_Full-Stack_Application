import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Equipment } from '../models/equipment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {

   private apiUrl ="http://localhost:8080/api/equipment/admin";


  constructor(private http:HttpClient) { }



  // create New Equipemnt card
  createNewEquipment(equipment: Equipment): Observable<Equipment> {
    return this.http.post<Equipment>(this.apiUrl, equipment);
  }


  //get all Equipments
  getEquipments():Observable<Equipment[]>{

      return this.http.get<Equipment[]>(this.apiUrl)
  }


  //get specific Equipment
  getEquipmentById(id:Number):Observable<Equipment>
  {
    const url = `${this.apiUrl}/${id}`
    return this.http.get<Equipment>(url);
  }


  //update Equipment
  updateEquipment(id:number,creditcard:Equipment):Observable<Equipment>
  {
    const url = `${this.apiUrl}/${id}`
    return this.http.put<Equipment>(url,creditcard);
  }


  // delete Equipment
deleteEquipment(id: Number): Observable<void> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.delete<void>(url);
}



}
