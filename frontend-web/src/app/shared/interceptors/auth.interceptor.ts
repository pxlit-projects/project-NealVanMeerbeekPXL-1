import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  req = req.clone({
    headers: req.headers.append('X-USER-ROLE', authService.isAdmin() ? 'ADMIN' : 'USER')
  });

  return next(req);
};
