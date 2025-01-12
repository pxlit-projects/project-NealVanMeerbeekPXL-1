import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewDetailComponent } from './review-detail.component';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { provideRouter } from '@angular/router';
import { provideCustomToastr } from '../../../app.config';

describe('ReviewDetailComponent', () => {
  let component: ReviewDetailComponent;
  let fixture: ComponentFixture<ReviewDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewDetailComponent],
      providers: [
        provideHttpClient(),
        DatePipe,
        provideRouter([]),
        provideCustomToastr
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
