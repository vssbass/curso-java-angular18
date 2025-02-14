import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { horario } from '../../interfaces/horario';

@Injectable({
  providedIn: 'root'
})
export class HorarioService {

  private apiHorarioAll = environment.apiUrl + 'horarios/getHorarios';
  private apiHorario = environment.apiUrl + 'horarios/horario-fecha';
  
  constructor(private http: HttpClient, private authService: AuthService) { }

  getHorario(fecha: String) {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<horario[]>(this.apiHorario, { fecha }, { headers });
  }

  getHorarioAll() {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<horario[]>(this.apiHorarioAll, { headers });
  }
}
