import { Component, inject } from '@angular/core';
import { FilterComponent } from '../filter/filter.component';
import { PostItemComponent } from '../post-item/post-item.component';
import { Filter } from '../../../shared/models/filter.model';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { PostService } from '../../../shared/services/post.service';
import type { Post } from '../../../shared/models/post.model';

@Component({
  selector: 'app-post-list',
  standalone: true,
  imports: [FilterComponent, PostItemComponent, AsyncPipe],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.css',
})
export class PostListComponent {
  postService: PostService = inject(PostService);
  filteredData$!: Observable<Post[]>;

  ngOnInit(): void {
    this.fetchData()
  }

  handleFilter(filter: Filter){
    this.filteredData$ = this.postService.filterPosts(filter);
  }

  private fetchData(): void {
    this.filteredData$ = this.postService.getPosts();
  }
}
