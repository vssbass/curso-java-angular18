import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { MenuService } from '../../core/services/menu.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  
  nameUserSession: string = '';

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.nameUserSession = this.authService.getUserName()?.toString() || '';
  }
  logout(): void {
    this.authService.logout();
  }

  toggleSidebar(event: Event) {
    event.preventDefault(); // Evita la recarga de la página
  
    const body = document.body;
    body.classList.toggle('sidebar-collapse');
    body.classList.toggle('sidebar-open');
  }
  
}
