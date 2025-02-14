import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { locales } from '../../interfaces/locales-optica';

@Injectable({
  providedIn: 'root'
})
export class LocalService {

  private apiLocales = environment.apiUrl + 'locales/getLocales';
  
  constructor(private http: HttpClient,private authService: AuthService) { }

  ngOnInit() {
    
  }

  getLocales() {
    const token = this.authService.getToken();
        const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        });
    return this.http.get<locales[]>(this.apiLocales, { headers });
  }
}
