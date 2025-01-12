import { Component, inject } from '@angular/core';
import { FilterComponent } from '../filter/filter.component';
import { PostItemComponent } from '../post-item/post-item.component';
import { Filter } from '../../../shared/models/filter.model';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import type { Post } from '../../../shared/models/post.model';
import type { ReadPostService } from '../../../shared/services/read-post.service';
import { READ_POST_SERVICE } from '../../../app.routes';

@Component({
  selector: 'app-post-list',
  standalone: true,
  imports: [FilterComponent, PostItemComponent, AsyncPipe],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.css',
})
export class PostListComponent {
  #postService: ReadPostService = inject(READ_POST_SERVICE);
  filteredData$: Observable<Post[]> = this.#postService.getPosts();

  handleFilter(filter: Filter){
    this.filteredData$ = this.#postService.filterPosts(filter);
  }
}
