import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { MenuUser, SubMenuItem } from '../../interfaces/menu-user';

@Injectable({
  providedIn: 'root'
})

export class MenuService {

  private apiMenu = environment.apiUrl + 'menu/list-user-menus-vo';
  private apiSubmenu = environment.apiUrl + 'menu/list-user-submenu-vo';

  private sidebarClosed = new BehaviorSubject<boolean>(false);
  sidebarState$ = this.sidebarClosed.asObservable();
  
  toggleSidebar() {
    this.sidebarClosed.next(!this.sidebarClosed.value); // Cambia el estado
  }

  constructor(private http: HttpClient,private authService: AuthService ) { }

  getUserMenus(): Observable<any> {
    const token = this.authService.getToken();

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    const paramsMenu = {
      user_id : 1,
      authority_id : 1
    }

    return this.http.post<SubMenuItem>(this.apiMenu, paramsMenu, { headers });
  }

  getUserSubMenus(): Observable<any> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    const paramsMenu = {
      user_id : 1,
      authority_id : 1
    }
    return this.http.post<any>(this.apiSubmenu, paramsMenu, { headers });
  }
}
