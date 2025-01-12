import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';
import { CommonPostService } from './common-post.service';
import { DatePipe } from '@angular/common';
import type { Post } from '../models/post.model';

describe('YourService', () => {
  let service: CommonPostService;
  let httpTestingController: HttpTestingController;

  const mockPosts = [
    { id: '1', creationDate: '2025-01-01', author: 'John Doe', content: 'Angular testing is great!', title: 'Post 1', published: true, reviewStatus: 'Approved' },
    { id: '2', creationDate: '2025-01-02', author: 'Jane Smith', content: 'RxJS makes life easier.', title: 'Post 2', published: false, reviewStatus: 'Pending' },
    { id: '3', creationDate: '2025-01-01', author: 'Alice Johnson', content: 'Testing branches is crucial.', title: 'Post 3', published: true, reviewStatus: 'Approved' },
  ] satisfies Post[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        DatePipe
      ]
    });

    service = TestBed.inject(CommonPostService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should filter posts by creation date, author, and content', () => {
    const filter = {
      creationDate: '2025-01-01',
      author: 'john',
      content: 'angular'
    };

    service.filterPosts('/api/posts', filter).subscribe((filteredPosts) => {
      expect(filteredPosts.length).toBe(1);
      expect(filteredPosts[0].id).toBe('1');
    });

    const req = httpTestingController.expectOne('/api/posts');
    req.flush(mockPosts);
  });

  it('should filter posts by creation date only', () => {
    const filter = {
      creationDate: '2025-01-01',
      author: '',
      content: ''
    };

    service.filterPosts('/api/posts', filter).subscribe((filteredPosts) => {
      expect(filteredPosts.length).toBe(2);
      expect(filteredPosts[0].id).toBe('1');
      expect(filteredPosts[1].id).toBe('3');
    });

    const req = httpTestingController.expectOne('/api/posts');
    req.flush(mockPosts);
  });

  it('should filter posts by author only', () => {
    const filter = {
      creationDate: '',
      author: 'jane',
      content: ''
    };

    service.filterPosts('/api/posts', filter).subscribe((filteredPosts) => {
      expect(filteredPosts.length).toBe(1);
      expect(filteredPosts[0].id).toBe('2');
    });

    const req = httpTestingController.expectOne('/api/posts');
    req.flush(mockPosts);
  });

  it('should filter posts by content only', () => {
    const filter = {
      creationDate: '',
      author: '',
      content: 'testing'
    };

    service.filterPosts('/api/posts', filter).subscribe((filteredPosts) => {
      expect(filteredPosts.length).toBe(2);
      expect(filteredPosts[0].id).toBe('1');
    });

    const req = httpTestingController.expectOne('/api/posts');
    req.flush(mockPosts);
  });

  it('should return all posts if no filters are applied', () => {
    const filter = {
      creationDate: '',
      author: '',
      content: ''
    };

    service.filterPosts('/api/posts', filter).subscribe((filteredPosts) => {
      expect(filteredPosts.length).toBe(3);
    });

    const req = httpTestingController.expectOne('/api/posts');
    req.flush(mockPosts);
  });

  it('should handle case-insensitivity in author and content filters', () => {
    const filter = {
      creationDate: '',
      author: 'ALICE',
      content: 'CRUCIAL'
    };

    service.filterPosts('/api/posts', filter).subscribe((filteredPosts) => {
      expect(filteredPosts.length).toBe(1);
      expect(filteredPosts[0].id).toBe('3');
    });

    const req = httpTestingController.expectOne('/api/posts');
    req.flush(mockPosts);
  });
});
