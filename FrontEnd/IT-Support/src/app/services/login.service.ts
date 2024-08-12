import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient, private router: Router) { }

  loginRequest(loginData: any): Observable<any> {
    return this.http.post<any>(this.loginUrl, loginData);
  }

  handleLoginResponse(response: any): void {
    console.log("from service");
    if (response != null && response.access_token != null) {
      localStorage.setItem('access_token', response.access_token);
      this.router.navigateByUrl('/dashboard');
      console.log('User login was successful:', response.message);
    }
    else
    {
      this.router.navigateByUrl('/login');
    }
  }

  handleLoginError(error: any): void {
    console.error('Login request failed:', error);
  }
}
