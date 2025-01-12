import type { Observable } from "rxjs";
import type { Post } from "../models/post.model";
import type { Filter } from "../models/filter.model";

export interface ReadPostService {
  getPosts(): Observable<Post[]>;
  getPost(id: string): Observable<Post>;
  filterPosts(filter: Filter): Observable<Post[]>
}
