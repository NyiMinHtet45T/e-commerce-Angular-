import { Component, input, signal } from '@angular/core';

@Component({
  selector: 'app-title-contents',
  imports: [],
  templateUrl: './title-contents.component.html',
  styles: ``
})
export class TitleContentsComponent {

  title = input.required<string>();
}
