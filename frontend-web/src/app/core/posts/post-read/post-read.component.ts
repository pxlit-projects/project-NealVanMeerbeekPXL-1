import { Component, Input } from '@angular/core';
import type { Post } from '../../../shared/models/post.model';
import type { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-post-read',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './post-read.component.html',
  styleUrl: './post-read.component.css'
})
export class PostReadComponent {
  @Input() post$!: Observable<Post>;
}
