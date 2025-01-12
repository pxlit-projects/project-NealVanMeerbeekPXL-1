import { TestBed } from '@angular/core/testing';

import { ReviewService } from './review.service';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { Review } from '../models/review.model';
import { DatePipe } from '@angular/common';
import { environment } from '../../../environments/environment';

describe('ReviewService', () => {
  let service: ReviewService;
  let httpTestingController: HttpTestingController;
  let apiUrl: string = environment.apiUrl;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting(), DatePipe],
    });
    service = TestBed.inject(ReviewService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should retrieve reviews via GET /review/review', () => {
    const expectedReviews = [
      {
        reviewer: 'John Doe',
        status: 'Approved',
        post: {
          id: '1',
          title: 'Post 1',
          creationDate: '2021-01-01',
          author: 'Jane Doe',
          content: 'This is a post.',
        },
        comments: [
          {
            id: 1,
            content: 'Great post!',
          },
        ],
        id: crypto.randomUUID(),
        reviewDate: new Date().toISOString(),
      },
    ] satisfies Review[];

    service.getReviews().subscribe((reviews) => {
      expect(reviews).toEqual(expectedReviews);
    });

    const req = httpTestingController.expectOne(apiUrl + '/review/review');
    expect(req.request.method).toBe('GET');
    req.flush(expectedReviews);
  });

  afterEach(() => {
    httpTestingController.verify();
  });
});
