import { Routes } from '@angular/router';
import { AddPostComponent } from './core/posts/admin/add-post/add-post.component';
import { PostListComponent } from './core/posts/post-list/post-list.component';
import { PostDetailComponent } from './core/posts/post-detail/post-detail.component';
import { confirmLeaveGuard } from './shared/guards/confirm-leave.guard';
import { adminGuard } from './shared/guards/admin.guard';
import { ReviewListComponent } from './core/reviews/review-list/review-list.component';
import { ReviewDetailComponent } from './core/reviews/review-detail/review-detail.component';
import { InjectionToken } from '@angular/core';
import { AuthService } from './shared/services/auth.service';
import type { ReadPostService } from './shared/services/read-post.service';
import { PostService } from './shared/services/post.service';
import { PublicPostService } from './shared/services/public-post.service';
import { PageNotFoundComponent } from './core/error/page-not-found/page-not-found.component';

export const READ_POST_SERVICE = new InjectionToken<ReadPostService>('ReadPostService');

export function postServiceFactory(authService: AuthService): ReadPostService {
  return authService.isAdmin() ? new PostService() : new PublicPostService();
}

export const postServiceProvider = {
  provide: READ_POST_SERVICE,
  useFactory: postServiceFactory,
  deps: [AuthService],
};

export const routes: Routes = [
  { path: 'posts', component: PostListComponent },
  { path: 'posts/:id', component: PostDetailComponent },
  { path: 'add', component: AddPostComponent, canActivate: [adminGuard], canDeactivate: [confirmLeaveGuard] },
  { path: 'reviews', component: ReviewListComponent, canActivate: [adminGuard] },
  { path: 'reviews/:id', component: ReviewDetailComponent, canActivate: [adminGuard] },
  { path: '', redirectTo: 'posts', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];
