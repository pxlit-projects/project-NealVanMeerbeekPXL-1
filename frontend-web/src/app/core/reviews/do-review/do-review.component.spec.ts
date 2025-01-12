import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoReviewComponent } from './do-review.component';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { provideCustomToastr } from '../../../app.config';

describe('DoReviewComponent', () => {
  let component: DoReviewComponent;
  let fixture: ComponentFixture<DoReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoReviewComponent],
      providers: [
        provideHttpClient(),
        DatePipe,
        provideCustomToastr
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
