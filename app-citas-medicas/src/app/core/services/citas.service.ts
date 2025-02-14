import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from '../../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { citas } from '../../interfaces/citas-optica';
import { concat } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitasService {

  private apiRegisterCitas = environment.apiUrl + 'citas/register-cita';
  private apiActualizarCitas = environment.apiUrl + 'citas/update-cita';
  private apiListarCitas = environment.apiUrl + 'citas/getCitas';
  private apiListaCita = environment.apiUrl + 'citas/getCita/';
  constructor(private http: HttpClient, private authService: AuthService) { }

  registrarCita(cita: citas) {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    const citaRequest: citas = {
      id: 0,
      codigo: cita.codigo,
      documento: cita.documento,
      nombres: cita.nombres,
      email: cita.email,
      celular: cita.celular,
      estado: true,
      locales: { id: cita.locales?.id },
      motivo: { id: cita.motivo?.id },
      observaciones: cita.observaciones,
      horacita: cita.horacita,
      fechacita: cita.fechacita,
      creador: this.authService.getUserName()?.toString()
    } 
    //console.log(citaRequest);
    return this.http.post<citas>(this.apiRegisterCitas, citaRequest, { headers });
  }

  actualizarCita(cita: citas) {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    const citaRequest: citas = {
      id: cita.id,
      codigo: cita.codigo,
      documento: cita.documento,
      nombres: cita.nombres,
      email: cita.email,
      celular: cita.celular,
      estado: cita.estado,
      locales: { id: cita.locales?.id },
      motivo: { id: cita.motivo?.id },
      observaciones: cita.observaciones,
      horacita: cita.horacita,
      fechacita: cita.fechacita,
      creador: this.authService.getUserName()?.toString()
    } 
    return this.http.put<citas>(this.apiActualizarCitas, citaRequest, { headers });
  }

  listarCitas(){
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<citas[]>(this.apiListarCitas, { headers });
  }

  getCita(id: number){
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    const urlcita = this.apiListaCita+id.toString();
    return this.http.get<citas>(urlcita ,{ headers });
  }

}
