import { Component, inject, Input, type OnInit } from '@angular/core';
import type { Observable } from 'rxjs';
import type { Review } from '../../../shared/models/review.model';
import { ReviewService } from '../../../shared/services/review.service';
import { FormBuilder, ReactiveFormsModule, Validators, type FormArray, type FormControl, type FormGroup } from '@angular/forms';
import type { DoReview } from '../../../shared/models/do-review.model';
import { AsyncPipe, DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-do-review',
  standalone: true,
  imports: [ReactiveFormsModule, DatePipe, AsyncPipe],
  templateUrl: './do-review.component.html',
  styleUrl: './do-review.component.css'
})
export class DoReviewComponent {
  @Input() review$!: Observable<Review>;
  #reviewService: ReviewService = inject(ReviewService);
  #toastrService: ToastrService = inject(ToastrService);
  #formBuilder: FormBuilder = inject(FormBuilder);
  reviewForm: FormGroup = this.#formBuilder.group({
    reviewer: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
    comments: this.#formBuilder.array<FormControl<string | null>>([this.#formBuilder.control('', [Validators.required])]),
  });

  get comments() {
    return this.reviewForm.get('comments') as FormArray;
  }

  addCommentFormControl() {
    this.comments.push(this.#formBuilder.control(undefined));
  }

  submitReviewForm(id: string, approved: boolean) {
    const doReview: DoReview = {
      approved,
      ...this.reviewForm.value,
      comments: [
        ...this.reviewForm.value.comments
          .filter((comment: string | null | undefined) => comment?.trim() !== '')
          .map((comment: string) => ({ content: comment.trim() }))
      ],
    };
    this.#reviewService.doReview(id, doReview).subscribe(() => {
      this.#toastrService.success('Review submitted.');
      this.reviewForm.reset();
    });
  }
}
