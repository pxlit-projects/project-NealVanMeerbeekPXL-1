import { Component, EventEmitter, Output } from '@angular/core';
import { Filter } from '../../../shared/models/filter.model';
import { FormsModule, type NgForm } from '@angular/forms';

@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {
  filter: Filter = { creationDate: undefined, author: '', content: '' };
  @Output() filterChanged = new EventEmitter<Filter>();

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.filterChanged.emit(this.filter);
    }
  }
}
