import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostItemComponent } from './post-item.component';
import { provideRouter } from '@angular/router';
import { Post } from '../../../shared/models/post.model';

describe('PostItemComponent', () => {
  let component: PostItemComponent;
  let fixture: ComponentFixture<PostItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostItemComponent],
      providers: [
        provideRouter([])
      ],
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostItemComponent);
    component = fixture.componentInstance;
    component.post = new Post(
      '25-12-2021',
      'Post Title',
      'Post Author',
      true,
      'Approved',
      'Post Content',
      crypto.randomUUID()
    );
    fixture.detectChanges();
  });

  it('should display the post item', () => {
    expect(component).toBeTruthy();
  });
});
