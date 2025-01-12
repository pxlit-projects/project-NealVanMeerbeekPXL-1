import { Component, inject, Input, type OnInit } from '@angular/core';
import { PostService } from '../../../../shared/services/post.service';
import { FormBuilder, ReactiveFormsModule, Validators, type FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import type { Post } from '../../../../shared/models/post.model';
import type { Observable } from 'rxjs';
import type { UpdatePost } from '../../../../shared/models/update-post.model';
import { ReviewService } from '../../../../shared/services/review.service';

@Component({
  selector: 'app-post-edit',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './post-edit.component.html',
  styleUrl: './post-edit.component.css'
})
export class PostEditComponent implements OnInit {
  @Input() post$!: Observable<Post>;
  @Input() id!: string;
  post?: Post;
  formBuilder: FormBuilder = inject(FormBuilder);
  postForm: FormGroup = this.formBuilder.group({
    author: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
    title: ['', [Validators.required, Validators.minLength(16), Validators.maxLength(100)]],
    content: ['', [Validators.required, Validators.minLength(60)]],
  });
  postService: PostService = inject(PostService);
  reviewService: ReviewService = inject(ReviewService);
  router: Router = inject(Router);

  ngOnInit() {
    this.post$.subscribe(post => {
      if (post) {
        this.post = post;

        this.postForm.patchValue({
          author: post.author,
          title: post.title,
          content: post.content,
        });
      }
    });
  }

  onSubmit() {
    const updatePost: UpdatePost = {
      ...this.postForm.value
    };
    this.postService.updatePost(this.id, updatePost).subscribe(() => {
      this.postForm.reset();
      this.router.navigate(['/posts']);
    });
  }

  onSubmitForReview() {
    const updatePost: UpdatePost = {
      ...this.postForm.value
    };
    this.reviewService.submitForReview(this.id, updatePost).subscribe(() => {
      
    });
  }
}
