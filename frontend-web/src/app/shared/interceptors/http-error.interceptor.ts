import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  const toastrService = inject(ToastrService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 409) {
        displayErrorToastMessage(toastrService, error, 'This resource already exists.')
      } else if (error.status === 400) {
        displayErrorToastMessage(toastrService, error, 'Unknown client-side error.');
      }

      return throwError(() => error);
    })
  );
};

function displayErrorToastMessage(toastrService: ToastrService, error: HttpErrorResponse, defaultMessage: string) {
  let errorMessage = defaultMessage;
  if (error.error && error.error.message) {
    errorMessage = error.error.message;
  }
  toastrService.error(errorMessage, 'Error');
}

