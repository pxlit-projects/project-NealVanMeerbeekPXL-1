import { Component, inject, type OnInit } from '@angular/core';
import { ReviewService } from '../../../shared/services/review.service';
import { AsyncPipe } from '@angular/common';
import { ReviewItemComponent } from "../review-item/review-item.component";
import type { Observable } from 'rxjs';
import type { Review } from '../../../shared/models/review.model';

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [AsyncPipe, ReviewItemComponent],
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent implements OnInit {
  #reviewService = inject(ReviewService);
  reviews$?: Observable<Review[]>;

  ngOnInit(): void {
      this.reviews$ = this.#reviewService.getReviews();
  }
}
