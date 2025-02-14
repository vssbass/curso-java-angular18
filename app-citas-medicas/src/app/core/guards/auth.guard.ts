import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  if (typeof window !== 'undefined') {
    const token = sessionStorage.getItem('token');

    if (token && isValidToken(token)) {
      return true; // Permitir acceso si el token es válido
    }
  }

  setTimeout(() => {
    router.navigate(['/login']);
  }, 0); // Asegurar que la redirección ocurre correctamente

  return false; // Bloquea la navegación si no hay token válido

  function isValidToken(token: string): boolean {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const exp = payload.exp * 1000; 
      return Date.now() < exp; 
    } catch (error) {
      return false; 
    }
  }
};
