import { CommonModule } from '@angular/common';
import { Component, computed, EventEmitter, input, Output, signal } from '@angular/core';
import {MatIconModule} from '@angular/material/icon'
import { PageInfo } from '../../service/commons';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [MatIconModule, CommonModule],
  templateUrl: './pagination.component.html',
  styles: ``
})
export class PaginationComponent {

  @Output()
  onPageChange = new EventEmitter;

  @Output()
  onSizeChange = new EventEmitter;

  pageInfo = input<PageInfo | undefined>();
  link = computed(() => this.pageInfo()?.links);
  lastPage = computed(() => (this.pageInfo()?.totalPage || 0) - 1);
  current = computed(() => this.pageInfo()?.currentPage)
  

  linkChange(page: number): void {
    this.onPageChange.emit(page);
  }

  sizeChange(size: any): void {
    this.onSizeChange.emit(size)
  }
}

export interface PagerComponent {
  linkChange(page:number): void;
  sizeChange(size:number): void;
}