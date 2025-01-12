import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostEditComponent } from './post-edit.component';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { provideCustomToastr } from '../../../../app.config';
import { PostService } from '../../../../shared/services/post.service';
import { of } from 'rxjs';
import { Post } from '../../../../shared/models/post.model';

describe('PostEditComponent', () => {
  let component: PostEditComponent;
  let fixture: ComponentFixture<PostEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostEditComponent],
      providers: [
        provideHttpClient(),
        DatePipe,
        provideCustomToastr
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostEditComponent);
    component = fixture.componentInstance;
    component.post$ = of(
      new Post(
        '25-12-2021',
        'Post Title',
        'Post Author',
        true,
        'Approved',
        'Post Content',
        crypto.randomUUID()
      )
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
