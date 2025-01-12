import { TestBed } from '@angular/core/testing';

import { PostService } from './post.service';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';

describe('PostService', () => {
  let service: PostService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        DatePipe
      ]
    });
    service = TestBed.inject(PostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
