import { CommonModule } from '@angular/common';
import { Component, computed, effect, EventEmitter, input, Output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { CommentService } from '../../../../../service/api/comment.service';
import { T } from '@angular/cdk/keycodes';
import { LoginUser, LoginUserState } from '../../../../../service/security/login-user.state';
import { MessageService } from '../../../../../service/subject/message.service';
import { PageInfo } from '../../../../../service/commons';

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [MatIconModule, CommonModule, FormsModule],
  templateUrl: './comment.component.html',
  styles: ``
})
export class CommentComponent {

  rows = signal<number>(1);

  handleRows(row: number) {
    this.rows.set(row)
  }

  productId = input.required<number | undefined>();

  user = signal<LoginUser | undefined>(undefined);

  size = signal<number>(5);

  constructor(private commentService:CommentService, private loginUser:LoginUserState, private messageService:MessageService) {
    this.user.set(this.loginUser.user());

    this.size.set(5);

    effect(() => {
      if(this.productId()) {
          this.getAllComment();
          this.getAvgAndTotalComment();
      }
    })

  }

  handleSeeMore() {
    this.size.update(pre => pre + 5);
    console.log(this.size())
  }

  @Output()
  ratingEmitter = new EventEmitter<number>

  commentPage = signal<PageInfo | undefined>(undefined);
  last = computed(() => this.commentPage()?.last);
  commentList = computed(() => this.commentPage()?.contents || []);

  getAllComment() {
    this.commentService.getAllReview(this.productId(), this.size()).subscribe(result => {
      this.commentPage.set(result);
    })
  }

  commentText = '';
  rating = signal<number>(1);

  handleRating(index:number) {
    this.rating.set(index);
  }

  totalAndAvgRating = signal<any>(undefined);

  getAvgAndTotalComment = () => {
    this.commentService.getAvgRatingAndComment(this.productId()!).subscribe(result => {
      this.totalAndAvgRating.set(result);
      this.ratingEmitter.emit(result.avgRating);
    })
  }

  handleComment() {

    const commentObj = {
      rating : this.rating(),
      comment : this.commentText,
      userId : this.user()?.id,
      productId : this.productId()
    }
    this.commentService.createComment(commentObj).subscribe(result => {
      this.messageService.setMessage(result.message);
      this.getAllComment();
      this.getAvgAndTotalComment();
    });
    this.commentText = '',
    this.rating.set(1);
    this.rows.set(1);
  }

 
 

  
}
