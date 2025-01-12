import type { ReviewComment } from "./review-comment.model";
import type { ReviewPost } from "./review-post.model";

export class Review {
  constructor(
    public reviewer: string,
    public status: string,
    public post: ReviewPost,
    public comments: ReviewComment[],
    public id?: string,
    public reviewDate?: string,
  ) {}
}
