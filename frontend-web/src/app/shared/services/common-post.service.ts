import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, type Observable } from 'rxjs';
import type { Post } from '../models/post.model';
import { DatePipe } from '@angular/common';
import type { Filter } from '../models/filter.model';

@Injectable({
  providedIn: 'root'
})
export class CommonPostService {
  #http: HttpClient = inject(HttpClient);
  #datePipe: DatePipe = inject(DatePipe);
  #dateFormat: string = 'dd-MM-yyyy';

  getPosts(url: string): Observable<Post[]> {
    return this.#http.get<Post[]>(url).pipe(map((posts) => posts.map((post) => ({ ...post, creationDate: this.#datePipe.transform(post.creationDate, this.#dateFormat)! }))));
  }

  getPost(url: string, id: string): Observable<Post> {
    return this.#http.get<Post>(`${url}/${id}`).pipe(map((post) => ({ ...post, creationDate: this.#datePipe.transform(post.creationDate, this.#dateFormat)! })));
  }

  filterPosts(url: string, filter: Filter): Observable<Post[]> {
    return this.#http.get<Post[]>(url).pipe(map((posts) => posts.filter((post) => this.#isPostMatchingFilter(post, filter))));
  }

  #isPostMatchingFilter(post: Post, filter: Filter): boolean {
    const matchesCreationDate = filter.creationDate ? post.creationDate.split("T")[0] === filter.creationDate : true;
    const matchesAuthor = post.author.toLowerCase().includes(filter.author.toLowerCase());
    const matchesContent = post.content.toLowerCase().includes(filter.content.toLowerCase());

    return matchesCreationDate && matchesAuthor && matchesContent;
  }
}
