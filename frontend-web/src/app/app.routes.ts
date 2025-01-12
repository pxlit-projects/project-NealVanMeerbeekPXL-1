import { Routes } from '@angular/router';
import { AddPostComponent } from './core/posts/admin/add-post/add-post.component';
import { PostListComponent } from './core/posts/post-list/post-list.component';
import { PostDetailComponent } from './core/posts/post-detail/post-detail.component';
import { confirmLeaveGuard } from './shared/guards/confirm-leave.guard';
import { adminGuard } from './shared/guards/admin.guard';

export const routes: Routes = [
  { path: 'posts', component: PostListComponent},
  { path: 'post/:id', component: PostDetailComponent },
  { path: 'add', component: AddPostComponent, canActivate: [adminGuard], canDeactivate: [confirmLeaveGuard] },
  { path: '', redirectTo: 'posts', pathMatch: 'full' },
];
