import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import type { Review } from '../../../shared/models/review.model';

@Component({
  selector: 'app-review-item',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './review-item.component.html',
  styleUrl: './review-item.component.css'
})
export class ReviewItemComponent {
  @Input() review!: Review;
}
