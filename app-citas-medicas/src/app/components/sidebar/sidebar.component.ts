import { Component, OnInit } from '@angular/core';
import { MenuItem, MenuUser, SubMenuItem } from '../../interfaces/menu-user';
import { MenuService } from '../../core/services/menu.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-sidebar',
  imports: [CommonModule, RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {
  
  menus: MenuUser[] = [];
  submenus: MenuItem[] = [];
  sidebarClosed = false;

  constructor(private menuService: MenuService) { 
    
  }

  
  ngOnInit(): void {
    const excluirMenus = ['Actualizar Clientes', 'Actualizar Citas', 'Actualizar Locales','Registrar Locales'];
    this.menuService.getUserMenus().subscribe({
      next: (data : MenuUser[]) => {
        this.menus = data
        .map((menu: MenuUser) => ({
          ...menu,
          submenus: menu.submenus?.filter(sub => !excluirMenus.includes(sub.sub_menu_name)) 
        }))
        .filter(menu => menu.submenus && menu.submenus.length > 0);
      },
      error: (err) => {
        console.error('Error al obtener los menús', err);
      },
    });

  }

  
  // toggleMenu(event: Event) {
  //   event.preventDefault();
  //   const target = event.currentTarget as HTMLElement;
  //   const parentLi = target.closest('.nav-item.has-treeview');
    
  //   if (parentLi) {
  //     const subMenu = parentLi.querySelector('.nav-treeview') as HTMLElement;
  //     const icon = parentLi.querySelector('.nav-arrow') as HTMLElement;
  
  //     if (subMenu.style.display === 'none' || subMenu.style.display === '') {
  //       subMenu.style.display = 'block';
  //       icon.classList.remove('bi-chevron-right');
  //       icon.classList.add('bi-chevron-up');
  //     } else {
  //       subMenu.style.display = 'none';
  //       icon.classList.remove('bi-chevron-up');
  //       icon.classList.add('bi-chevron-right');
  //     }
  //   }
  // }
  toggleMenu(event: Event) {
    event.preventDefault();
    const target = event.currentTarget as HTMLElement;
    const parentLi = target.closest('.nav-item.has-treeview');
  
    if (parentLi) {
      const subMenu = parentLi.querySelector('.nav-treeview') as HTMLElement;
      const icon = parentLi.querySelector('.nav-arrow') as HTMLElement;
  
      if (!subMenu) return;
  
      const isOpen = subMenu.classList.contains('show');
  
      // Cierra todos los demás submenús abiertos
      document.querySelectorAll('.nav-treeview.show').forEach((openMenu) => {
        if (openMenu !== subMenu) {
          openMenu.classList.remove('show');
          openMenu.removeAttribute('style'); 
        }
      });
  
      if (isOpen) {
        subMenu.classList.remove('show');
        subMenu.removeAttribute('style'); 
        icon.classList.remove('bi-chevron-up');
        icon.classList.add('bi-chevron-right');
        subMenu.style.display = 'none';
      } else {
        subMenu.classList.add('show');
        subMenu.style.display = 'block'; 
        icon.classList.remove('bi-chevron-right');
        icon.classList.add('bi-chevron-up');
      }
    }
  }

  
  
}
