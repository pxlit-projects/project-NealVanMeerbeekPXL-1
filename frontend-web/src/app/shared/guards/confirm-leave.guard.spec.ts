import { TestBed } from '@angular/core/testing';
import { CanDeactivateFn } from '@angular/router';

import { confirmLeaveGuard } from './confirm-leave.guard';
import type { AddPostComponent } from '../../core/posts/admin/add-post/add-post.component';

describe('confirmLeaveGuard', () => {
  const executeGuard: CanDeactivateFn<AddPostComponent> = (...guardParameters) =>
      TestBed.runInInjectionContext(() => confirmLeaveGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
