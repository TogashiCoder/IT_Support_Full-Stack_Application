import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';


interface AuthResponse {
  access_token: string;
  message: string;
  character_id: number;
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = 'http://localhost:8080';
  private readonly TOKEN_KEY = 'jwt_token';

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService
  ) {}

  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, { username, password })
      .pipe(
        tap(response => {
          localStorage.setItem(this.TOKEN_KEY, response.access_token);
        })
      );
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem(this.TOKEN_KEY);
    return token != null && !this.jwtHelper.isTokenExpired(token);
  }

  getUserIdFromJwt(): number | null {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.id;
    }
    return null;
  }

  getCharacterId(): number | null {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.character_id;
    }
    return null;
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getUserRole(): string {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.role; // Assuming the role is stored in the JWT
    }
    return '';
  }



  //  get the id from the JWT token
  getId(): number | null {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.id;
    }
    return null;
  }


}
