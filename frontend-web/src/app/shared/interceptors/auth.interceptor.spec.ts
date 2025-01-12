import { TestBed } from '@angular/core/testing';
import { HTTP_INTERCEPTORS, HttpClient, provideHttpClient, withInterceptors } from '@angular/common/http';

import { authInterceptor } from './auth.interceptor';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { AuthService } from '../services/auth.service';

describe('authInterceptor', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let authService: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    authService = jasmine.createSpyObj('AuthService', ['isAdmin']); // Create a mock

    TestBed.configureTestingModule({
      providers: [
        { provide: AuthService, useValue: authService },
        provideHttpClient(withInterceptors([authInterceptor])),
        provideHttpClientTesting()
      ],
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify(); // Verify that there are no outstanding requests
  });

  it('should add X-USER-ROLE header with ADMIN role when user is admin', () => {
    authService.isAdmin.and.returnValue(true); // Mock isAdmin to return true

    httpClient.get('/test').subscribe();

    const req = httpTestingController.expectOne('/test');
    expect(req.request.headers.has('X-USER-ROLE')).toBeTruthy();
    expect(req.request.headers.get('X-USER-ROLE')).toBe('ADMIN');
    req.flush(null);
  });

  it('should add X-USER-ROLE header with USER role when user is not admin', () => {
    authService.isAdmin.and.returnValue(false); // Mock isAdmin to return false

    httpClient.get('/test').subscribe();

    const req = httpTestingController.expectOne('/test');
    expect(req.request.headers.has('X-USER-ROLE')).toBeTruthy();
    expect(req.request.headers.get('X-USER-ROLE')).toBe('USER');
    req.flush(null);
  });
});
