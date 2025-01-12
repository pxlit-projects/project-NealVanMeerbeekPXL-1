import { TestBed } from '@angular/core/testing';

import { PublicPostService } from './public-post.service';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';

describe('PublicPostService', () => {
  let service: PublicPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        DatePipe
      ]
    });
    service = TestBed.inject(PublicPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
