import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { type Observable } from 'rxjs';
import type { Post } from '../models/post.model';
import { CommonPostService } from './common-post.service';
import type { ReadPostService } from './read-post.service';
import type { Filter } from '../models/filter.model';

@Injectable({
  providedIn: 'root'
})
export class PublicPostService implements ReadPostService {
  #api: string = `${environment.apiUrl}/post/public/post`;
  #commonPostService: CommonPostService = inject(CommonPostService);

  getPosts(): Observable<Post[]> {
    return this.#commonPostService.getPosts(this.#api);
  }

  getPost(id: string): Observable<Post> {
    return this.#commonPostService.getPost(this.#api, id);
  }

  filterPosts(filter: Filter): Observable<Post[]> {
    return this.#commonPostService.filterPosts(this.#api, filter);
  }
}
