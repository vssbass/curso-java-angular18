import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MotivoService {

  private apiMotivos = environment.apiUrl + 'motivos/getMotivos';
  
  constructor(private http: HttpClient, private authService: AuthService) { }

  getMotivos() {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<any>(this.apiMotivos, { headers });
  }
}
