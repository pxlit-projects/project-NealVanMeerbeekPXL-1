import { TestBed } from '@angular/core/testing';

import { PostService } from './post.service';
import { provideHttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import type { Post } from '../models/post.model';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { environment } from '../../../environments/environment';

describe('PostService', () => {
  let service: PostService;
  let httpTestingController: HttpTestingController;
  let apiUrl: string = environment.apiUrl;
  let datePipe: DatePipe;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        DatePipe
      ]
    });
    service = TestBed.inject(PostService);
    httpTestingController = TestBed.inject(HttpTestingController);
    datePipe = TestBed.inject(DatePipe);
  });

  it('should retrieve posts via GET /post/post', () => {
    const expectedPosts = [
      {
        id: '1',
        title: 'Post 1',
        creationDate: new Date(2021, 1, 1).toISOString(),
        author: 'Jane Doe',
        content: 'This is a post.',
        published: true,
        reviewStatus: 'Approved'
      },
      {
        id: '2',
        title: 'Post 2',
        creationDate: new Date(2021, 1, 2).toISOString(),
        author: 'John Doe',
        content: 'This is another post.',
        published: false,
        reviewStatus: 'Pending'
      }
    ] satisfies Post[];

    service.getPosts().subscribe((posts) => {
      expect(posts).toEqual(expectedPosts.map((post) => ({ ...post, creationDate: datePipe.transform(post.creationDate, "dd-MM-yyyy")! })));
    });

    const req = httpTestingController.expectOne(apiUrl + '/post/post');
    expect(req.request.method).toBe('GET');
    req.flush(expectedPosts);
  });

  it('should retrieve a post via GET /post/post/:id', () => {
    const expectedPost = {
      id: '1',
      title: 'Post 1',
      creationDate: new Date(2021, 1, 1).toISOString(),
      author: 'Jane Doe',
      content: 'This is a post.',
      published: true,
      reviewStatus: 'Approved'
    } satisfies Post;

    service.getPost('1').subscribe((post) => {
      expect(post).toEqual({ ...expectedPost, creationDate: datePipe.transform(expectedPost.creationDate, "dd-MM-yyyy")! });
    });

    const req = httpTestingController.expectOne(apiUrl + '/post/post/1');
    expect(req.request.method).toBe('GET');
    req.flush(expectedPost);
  });

  it('should update a post via PUT /post/post/:id', () => {
    const post = {
      id: '1',
      title: 'Post 1',
      creationDate: new Date(2021, 1, 1).toISOString(),
      author: 'Jane Doe',
      content: 'This is a post.',
      published: true,
      reviewStatus: 'Approved'
    } as Post;

    service.updatePost('1', post).subscribe((updatedPost) => {
      expect(updatedPost).toEqual(post);
    });

    const req = httpTestingController.expectOne(apiUrl + '/post/post/1');
    expect(req.request.method).toBe('PUT');
    req.flush(post);
  });

  it('should submit a post for review via POST /post/review/:id', () => {
    const post = {
      title: 'Post 1',
      author: 'Jane Doe',
      content: 'This is a post.'
    } as Post;

    service.submitForReview('1', post).subscribe((submittedPost) => {
      expect(submittedPost).toEqual(post);
    });

    const req = httpTestingController.expectOne(apiUrl + '/post/review/1');
    expect(req.request.method).toBe('POST');
    req.flush(post);
  });

  it('should publish a post via PATCH /post/post/:id/published', () => {
    service.publishPost('1').subscribe(() => {
      expect().nothing();
    });

    const req = httpTestingController.expectOne(apiUrl + '/post/post/1/published');
    expect(req.request.method).toBe('PATCH');
    req.flush(null);
  });
});
