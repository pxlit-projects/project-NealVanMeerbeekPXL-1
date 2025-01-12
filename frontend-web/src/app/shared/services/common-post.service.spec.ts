import { TestBed } from '@angular/core/testing';

import { CommonPostService } from './common-post.service';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';

describe('CommonPostService', () => {
  let service: CommonPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        DatePipe
      ]
    });
    service = TestBed.inject(CommonPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
