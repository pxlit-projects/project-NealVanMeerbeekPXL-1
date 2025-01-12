import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import type { Review } from '../models/review.model';
import { DatePipe } from '@angular/common';
import { map } from 'rxjs';
import type { DoReview } from '../models/do-review.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  #api: string = `${environment.apiUrl}/review/review`;
  #http: HttpClient = inject(HttpClient);
  #datePipe: DatePipe = inject(DatePipe);

  getReviews() {
    return this.#http.get<Review[]>(this.#api).pipe(map((reviews) => reviews.map((review) => ({ ...review, post: { ...review.post, creationDate: this.#datePipe.transform(review.post.creationDate, 'yyyy-MM-dd')! } }))));
  }

  getReviewById(id: string) {
    return this.#http.get<Review>(`${this.#api}/${id}`);
  }

  doReview(id: string, review: DoReview) {
    return this.#http.put<void>(`${this.#api}/${id}`, review);
  }
}
