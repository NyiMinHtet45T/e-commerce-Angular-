import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-form-contents',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './form-contents.component.html',
  styles: ``
})
export class FormContentsComponent {

  label = input.required();

  touched = input.required();
  invalid = input.required();
}
