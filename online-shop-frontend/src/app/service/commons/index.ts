export interface PageInfo {
   contents: any[],
   totalPage: number,
   totalCount: number,
   currentPage: number,
   pageSize: number,
   last: boolean,
   links: number[]
}