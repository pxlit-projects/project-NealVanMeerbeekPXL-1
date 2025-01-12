import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { map, type Observable } from 'rxjs';
import type { Post } from '../models/post.model';
import type { Filter } from '../models/filter.model';
import type { AddPost } from '../models/add-post.model';
import { DatePipe } from '@angular/common';
import type { UpdatePost } from '../models/update-post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  api: string = `${environment.apiUrl}/post/post`;
  http: HttpClient = inject(HttpClient);
  datePipe: DatePipe = inject(DatePipe);

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.api).pipe(map((posts) => posts.map((post) => ({ ...post, creationDate: this.datePipe.transform(post.creationDate, 'dd-MM-yyyy')! }))));
  }

  getPost(id: string): Observable<Post> {
    return this.http.get<Post>(`${this.api}/${id}`).pipe(map((post) => ({ ...post, creationDate: this.datePipe.transform(post.creationDate, 'dd-MM-yyyy')! })));
  }

  addPost(post: AddPost): Observable<Post> {
    return this.http.post<Post>(this.api, post);
  }

  updatePost(id: string, post: UpdatePost): Observable<Post> {
    return this.http.put<Post>(`${this.api}/${id}`, post);
  }

  publishPost(id: string) {
    return this.http.patch<never>(`${this.api}/${id}/published`, {
      published: true
    });
  }

  filterPosts(filter: Filter): Observable<Post[]> {
    return this.http.get<Post[]>(this.api).pipe(map((posts) => posts.filter((post) => this.isPostMatchingFilter(post, filter))));
  }

  private isPostMatchingFilter(post: Post, filter: Filter): boolean {
    const matchesCreationDate = filter.creationDate ? post.creationDate.split("T")[0] === filter.creationDate : true;
    const matchesAuthor = post.author.toLowerCase().includes(filter.author.toLowerCase());
    const matchesContent = post.content.toLowerCase().includes(filter.content.toLowerCase());

    return matchesCreationDate && matchesAuthor && matchesContent;
  }
}
