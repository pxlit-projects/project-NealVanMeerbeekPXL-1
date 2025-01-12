import { Component, inject, Input, type OnInit } from '@angular/core';
import { PostService } from '../../../../shared/services/post.service';
import { FormBuilder, ReactiveFormsModule, Validators, type FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import type { Post } from '../../../../shared/models/post.model';
import type { Observable } from 'rxjs';
import type { UpdatePost } from '../../../../shared/models/update-post.model';
import { ToastrService } from 'ngx-toastr';
import { LowerCasePipe } from '@angular/common';

@Component({
  selector: 'app-post-edit',
  standalone: true,
  imports: [ReactiveFormsModule, LowerCasePipe],
  templateUrl: './post-edit.component.html',
  styleUrl: './post-edit.component.css'
})
export class PostEditComponent implements OnInit {
  @Input() post$!: Observable<Post>;
  post?: Post;
  #formBuilder: FormBuilder = inject(FormBuilder);
  postForm: FormGroup = this.#formBuilder.group({
    author: [{ value: '', disabled: this.post?.reviewStatus === 'Not yet performed' }, [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
    title: [{ value: '', disabled: this.post?.reviewStatus === 'Not yet performed' }, [Validators.required, Validators.minLength(16), Validators.maxLength(100)]],
    content: [{ value: '', disabled: this.post?.reviewStatus === 'Not yet performed' }, [Validators.required, Validators.minLength(60)]],
  });
  #postService: PostService = inject(PostService);
  #toastrService: ToastrService = inject(ToastrService);
  #router: Router = inject(Router);

  ngOnInit() {
    this.post$.subscribe(post => {
      this.post = post;
      this.#disableFormIfAlreadySubmitted();

      this.postForm.patchValue({
        author: post.author,
        title: post.title,
        content: post.content,
      });
    });
  }

  onSubmit() {
    const updatePost: UpdatePost = {
      ...this.postForm.value
    };
    this.#postService.updatePost(this.post!.id!, updatePost).subscribe((post) => {
      this.post = post;
      this.postForm.reset({ disabled: true });
      this.#router.navigate(['/posts']);
    });
  }

  onSubmitForReview() {
    const updatePost: UpdatePost = {
      ...this.postForm.value
    };
    this.#postService.submitForReview(this.post!.id!, updatePost).subscribe((post) => {
      this.post = post;
      this.postForm.reset({ disabled: true });
      this.#toastrService.success('Post submitted for review.');
    });
  }

  onPublish() {
    this.#postService.publishPost(this.post!.id!).subscribe(() => {
      this.post!.published = true;
      this.postForm.reset({ disabled: true });
      this.#toastrService.success('Post published.');
    });
  }

  #disableFormIfAlreadySubmitted() {
    if (this.post?.reviewStatus !== 'Not yet performed') {
      this.postForm.disable();
    }
  }
}
