import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostDetailComponent } from './post-detail.component';
import { postServiceProvider } from '../../../app.routes';
import { AuthService } from '../../../shared/services/auth.service';
import { provideRouter } from '@angular/router';

// describe('PostDetailComponent', () => {
//   let component: PostDetailComponent;
//   let fixture: ComponentFixture<PostDetailComponent>;

//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       imports: [PostDetailComponent, AuthService],
//       providers: [
//         postServiceProvider,
//         provideRouter([])
//       ],
//     })
//     .compileComponents();

//     fixture = TestBed.createComponent(PostDetailComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });
