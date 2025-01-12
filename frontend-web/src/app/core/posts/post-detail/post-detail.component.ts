import { Component, OnDestroy, inject } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { PostService } from '../../../shared/services/post.service';
import { Post } from '../../../shared/models/post.model';
import { Observable, Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import type { UpdatePost } from '../../../shared/models/update-post.model';

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent implements OnDestroy {
  postService: PostService = inject(PostService);
  route: ActivatedRoute = inject(ActivatedRoute);
  id: string = this.route.snapshot.params['id'];
  sub!: Subscription;

  post$: Observable<Post> = this.postService.getPost(this.id);

  // togglePublished(post: UpdatePost) {
  //   post.published = !post.published;
  //   this.sub = this.postService.updatePost(post).subscribe({
  //     next: () => {
  //       console.log('done!');
  //     },
  //   });
  // }

  ngOnDestroy(): void {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }
}
