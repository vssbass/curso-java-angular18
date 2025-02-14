import { Routes } from '@angular/router';
import { LoginComponent } from './layout/login/login.component';
import { DashboardComponent } from './Pages/dashboard/dashboard.component';
import { CitasRegisterComponent } from './Pages/gestion/citas/citas-register/citas-register.component';
import { CitasUpdateComponent } from './Pages/gestion/citas/citas-update/citas-update.component';
import { CitasAllComponent } from './Pages/gestion/citas/citas-all/citas-all.component';
import { LocalAllComponent } from './Pages/gestion/local/local-all/local-all.component';
import { LocalRegisterComponent } from './Pages/gestion/local/local-register/local-register.component';
import { LocalUpdateComponent } from './Pages/gestion/local/local-update/local-update.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { authGuard } from './core/guards/auth.guard';
import { HorariosAllComponent } from './Pages/gestion/horarios/horarios-all/horarios-all.component';



export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'citas',
    component: MainLayoutComponent, canActivate: [authGuard],
    children: [
      { path: 'buscar-citas', component: CitasAllComponent },
      { path: 'registrar-citas', component: CitasRegisterComponent },
      { path: 'actualizar-citas/:id', component: CitasUpdateComponent },
    ]
  },
  {
    path: 'locales',
    component: MainLayoutComponent, canActivate: [authGuard],
    children: [
      { path: 'buscar-locales', component: LocalAllComponent },
      { path: 'registrar-locales', component: LocalRegisterComponent },
      { path: 'actualizar-locales/:id', component: LocalUpdateComponent },
    ]
  },
  {
    path: 'reportes',
    component: MainLayoutComponent, canActivate: [authGuard],
    children: [
      { path: 'buscar-horarios', component: HorariosAllComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    ]
  },
  {
    path: 'dashboard',
    component: MainLayoutComponent, canActivate: [authGuard], // Layout principal
    children: [
      { path: '', component: DashboardComponent },
    ],
  },
  //{ path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  //ruta comodin, cualquier ruta que no este definida
  { path: '**', redirectTo: 'dashboard' }
  //{ path: '**', redirectTo: 'citas' }
];



