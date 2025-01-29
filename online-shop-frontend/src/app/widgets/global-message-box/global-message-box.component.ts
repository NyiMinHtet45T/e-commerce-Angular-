import { Component, signal } from '@angular/core';
import { MessageService } from '../../service/subject/message.service';

@Component({
  selector: 'app-global-message-box',
  imports: [],
  templateUrl: './global-message-box.component.html',
  styleUrl: './global-message-box.component.css'
})
export class GlobalMessageBoxComponent {

  message:string | null = null;
  showMessage = signal<boolean>(false);

  constructor(private messageService: MessageService) {
      this.messageService.message$.subscribe(result => {
      this.message = result;
      if(this.message != null) {
        this.showMessage.set(true);
        this.timer();
      }
     
    })
  }

  timer() {
    setTimeout(() => {
      this.showMessage.set(false)
      this.messageService.clearMessage();
    }, 4000)
  }
}
