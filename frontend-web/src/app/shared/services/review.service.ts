import { inject, Injectable } from '@angular/core';
import type { UpdatePost } from '../models/update-post.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  api: string = `${environment.apiUrl}/review/review`;
  http: HttpClient = inject(HttpClient);

  submitForReview(id: string, post: UpdatePost) {
    return this.http.post<never>(`${environment.apiUrl}/post/review/${id}`, post);
  }
}
