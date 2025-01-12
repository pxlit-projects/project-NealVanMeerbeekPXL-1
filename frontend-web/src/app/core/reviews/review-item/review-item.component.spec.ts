import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewItemComponent } from './review-item.component';
import { provideRouter } from '@angular/router';
import { Review } from '../../../shared/models/review.model';
import { ReviewPost } from '../../../shared/models/review-post.model';
import { ReviewComment } from '../../../shared/models/review-comment.model';

describe('ReviewItemComponent', () => {
  let component: ReviewItemComponent;
  let fixture: ComponentFixture<ReviewItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewItemComponent],
      providers: [
        provideRouter([])
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewItemComponent);
    component = fixture.componentInstance;
    component.review = new Review(
      'Reviewer Name',
      'Pending',
      new ReviewPost(
        crypto.randomUUID(),
        '22-12-2021',
        'Post Title',
        'Post Author',
        'Post Content'
      ),
      [
        new ReviewComment(
          'Well done',
          1
        )
      ]
    )
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
