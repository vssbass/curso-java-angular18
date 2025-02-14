import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiLogin = environment.apiUrl + 'v1/auth/login-body'; // URL desde environment
  constructor(private http: HttpClient) { }

  login(userName: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiLogin, { userName, password });
  }

  setUserName(username: string): void {
    sessionStorage.setItem('userName', username);
  }

  getUserName(): string | null {
    return sessionStorage.getItem('userName');
  }

  setToken(token: string): void {
    sessionStorage.setItem('token', token);
  }

  getToken(): string | null {
    return sessionStorage.getItem('token');
  }

  logout(): void {
    sessionStorage.removeItem('token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
