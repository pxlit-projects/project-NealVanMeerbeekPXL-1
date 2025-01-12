import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AddPost } from '../../../../shared/models/add-post.model';
import { Router } from '@angular/router';
import { PostService } from '../../../../shared/services/post.service';

@Component({
  selector: 'app-add-post',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-post.component.html',
  styleUrl: './add-post.component.css',
})
export class AddPostComponent {
  #formBuilder: FormBuilder = inject(FormBuilder);
  postForm: FormGroup = this.#formBuilder.group({
    author: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
    title: ['', [Validators.required, Validators.minLength(16), Validators.maxLength(100)]],
    content: ['', [Validators.required, Validators.minLength(60)]],
  });
  #postService: PostService = inject(PostService);
  #router: Router = inject(Router);

  onSubmit() {
    const newPost: AddPost = {
      id: crypto.randomUUID(),
      ...this.postForm.value
    };
    this.#postService.addPost(newPost).subscribe(() => {
      this.postForm.reset();
      this.#router.navigate(['/posts']);
    });
  }
}
