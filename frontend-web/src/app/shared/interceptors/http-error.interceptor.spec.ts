import { TestBed } from '@angular/core/testing';
import { provideHttpClient, withInterceptors, HttpClient, type HttpErrorResponse } from '@angular/common/http';

import { httpErrorInterceptor } from './http-error.interceptor';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { ToastrService } from 'ngx-toastr';
import { provideCustomToastr } from '../../app.config';

describe('httpErrorInterceptor', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let toastrService: jasmine.SpyObj<ToastrService>;

  beforeEach(() => {
    toastrService = jasmine.createSpyObj('ToastrService', ['error']);

    TestBed.configureTestingModule({
      providers: [
        { provide: ToastrService, useValue: toastrService },
        provideHttpClient(withInterceptors([httpErrorInterceptor])),
        provideHttpClientTesting(),
        provideCustomToastr
      ],
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should show toast for 409 error', () => {
    const mockErrorResponse = { status: 409, statusText: 'Conflict', error: { message: 'Resource exists' } };

    httpClient.get('/test').subscribe({
      next: () => fail('Should have failed with 409 error'),
      error: (error: HttpErrorResponse) => {
        expect(error.status).toBe(409);
      }
    });

    const req = httpTestingController.expectOne('/test');
    req.flush(mockErrorResponse.error, mockErrorResponse);

    expect(toastrService.error).toHaveBeenCalledWith('Resource exists', 'Error');
  });

  it('should show toast for 400 error', () => {
    const mockErrorResponse = { status: 400, statusText: 'Bad Request', error: {} };

    httpClient.get('/test').subscribe({
      next: () => fail('Should have failed with 400 error'),
      error: (error: HttpErrorResponse) => {
        expect(error.status).toBe(400);
      }
    });

    const req = httpTestingController.expectOne('/test');
    req.flush(mockErrorResponse.error, mockErrorResponse);

    expect(toastrService.error).toHaveBeenCalledWith('Unknown client-side error.', 'Error');
  });

  it('should use default message if no error message is provided', () => {
    const mockErrorResponse = { status: 409, statusText: 'Conflict', error: {} };

    httpClient.get('/test').subscribe({
      next: () => fail('Should have failed with 409 error'),
      error: (error: HttpErrorResponse) => {
        expect(error.status).toBe(409);
      }
    });

    const req = httpTestingController.expectOne('/test');
    req.flush(mockErrorResponse.error, mockErrorResponse);

    expect(toastrService.error).toHaveBeenCalledWith('This resource already exists.', 'Error');
  });

  it('should pass through requests without errors', () => {
    const mockResponse = { data: 'test' };

    httpClient.get('/test').subscribe({
      next: (response) => {
        expect(response).toEqual(mockResponse);
      },
      error: () => fail('Should not have failed')
    });

    const req = httpTestingController.expectOne('/test');
    req.flush(mockResponse);

    expect(toastrService.error).not.toHaveBeenCalled();
  });
});
