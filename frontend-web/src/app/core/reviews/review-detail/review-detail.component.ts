import { Component, inject } from '@angular/core';
import type { Review } from '../../../shared/models/review.model';
import { ReviewService } from '../../../shared/services/review.service';
import { ActivatedRoute } from '@angular/router';
import type { Observable } from 'rxjs';
import { DoReviewComponent } from "../do-review/do-review.component";

@Component({
  selector: 'app-review-detail',
  standalone: true,
  imports: [DoReviewComponent],
  templateUrl: './review-detail.component.html',
  styleUrl: './review-detail.component.css'
})
export class ReviewDetailComponent {
  #reviewService: ReviewService = inject(ReviewService);
  #route: ActivatedRoute = inject(ActivatedRoute);
  #id: string = this.#route.snapshot.params['id'];
  review$: Observable<Review> = this.#reviewService.getReviewById(this.#id);
}
