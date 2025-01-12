import { Component, inject } from '@angular/core';
import { PostReadComponent } from "../post-read/post-read.component";
import { ActivatedRoute } from '@angular/router';
import type { Observable } from 'rxjs';
import type { Post } from '../../../shared/models/post.model';
import { PostEditComponent } from "../admin/post-edit/post-edit.component";
import { AuthService } from '../../../shared/services/auth.service';
import { READ_POST_SERVICE } from '../../../app.routes';
import type { ReadPostService } from '../../../shared/services/read-post.service';

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [PostReadComponent, PostEditComponent],
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent {
  #postService: ReadPostService = inject(READ_POST_SERVICE);
  #authService: AuthService = inject(AuthService);
  isAdmin = this.#authService.isAdmin();
  #route: ActivatedRoute = inject(ActivatedRoute);
  #id: string = this.#route.snapshot.params['id'];
  post$: Observable<Post> = this.#postService.getPost(this.#id);
}
