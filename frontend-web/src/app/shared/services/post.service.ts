import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { type Observable } from 'rxjs';
import type { Post } from '../models/post.model';
import type { Filter } from '../models/filter.model';
import type { AddPost } from '../models/add-post.model';
import type { UpdatePost } from '../models/update-post.model';
import { CommonPostService } from './common-post.service';
import type { ReadPostService } from './read-post.service';

@Injectable({
  providedIn: 'root'
})
export class PostService implements ReadPostService {
  #api: string = `${environment.apiUrl}/post/post`;
  #http: HttpClient = inject(HttpClient);
  #commonPostService: CommonPostService = inject(CommonPostService);

  getPosts(): Observable<Post[]> {
    return this.#commonPostService.getPosts(this.#api);
  }

  getPost(id: string): Observable<Post> {
    return this.#commonPostService.getPost(this.#api, id);
  }

  addPost(post: AddPost): Observable<Post> {
    return this.#http.post<Post>(this.#api, post);
  }

  updatePost(id: string, post: UpdatePost): Observable<Post> {
    return this.#http.put<Post>(`${this.#api}/${id}`, post);
  }

  submitForReview(id: string, post: UpdatePost) {
    return this.#http.post<Post>(`${environment.apiUrl}/post/review/${id}`, post);
  }

  publishPost(id: string) {
    return this.#http.patch<void>(`${this.#api}/${id}/published`, {
      published: true
    });
  }

  filterPosts(filter: Filter): Observable<Post[]> {
    return this.#commonPostService.filterPosts(this.#api, filter);
  }
}
