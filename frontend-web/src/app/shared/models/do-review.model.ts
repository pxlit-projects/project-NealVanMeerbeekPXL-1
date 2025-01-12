import type { ReviewComment } from "./review-comment.model";

export class DoReview {
  constructor(
    public approved: boolean,
    public reviewer: string,
    public comments: ReviewComment[],
  ) {}
}
