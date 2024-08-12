import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl ="http://localhost:8080/api/users";


  constructor(private http:HttpClient) { }



  // create New User
  createNewUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }


  //get all Users
  getUsers():Observable<User[]>{

      return this.http.get<User[]>(this.apiUrl)
  }


  //get specific User
  getUserById(id:Number):Observable<User>
  {
    const url = `${this.apiUrl}/${id}`
    return this.http.get<User>(url);
  }


  //update User
  updateUser(id:number,user:User):Observable<User>
  {
    const url = `${this.apiUrl}/${id}`
    return this.http.put<User>(url,user);
  }


  // delete user
deleteUser(id: Number): Observable<void> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.delete<void>(url);
}}
