import { Component, Input } from '@angular/core';
import { Post } from '../../../shared/models/post.model';
import { NgClass } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-post-item',
  standalone: true,
  imports: [NgClass, RouterLink],
  templateUrl: './post-item.component.html',
  styleUrl: './post-item.component.css'
})
export class PostItemComponent {
  @Input() post!: Post;

  getDetails(): void{
    console.log(this.post);
  }
}
