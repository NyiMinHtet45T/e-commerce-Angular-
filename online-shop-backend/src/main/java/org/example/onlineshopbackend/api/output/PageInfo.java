package org.example.onlineshopbackend.api.output;

import org.example.onlineshopbackend.model.entity.Category;
import org.example.onlineshopbackend.model.utils.PageLinkFactory;
import org.springframework.data.domain.Page;

import java.util.List;

public record PageInfo<T>(
        List<T> contents,
        int totalPage,
        long totalCount,
        int currentPage,
        int pageSize,
        boolean last,
        List<Integer> links
) {

    public static<T> Builder<T> getBuilder() {
        return new Builder<T>();
    }

    public static<T> PageInfo<T> getPageInfo(Page<T> page) {
        Builder<T> builder = getBuilder();
        return builder.contents(page.getContent())
                .totalPage(page.getTotalPages())
                .totalCount(page.getTotalElements())
                .currentPage(page.getNumber())
                .pageSize(page.getSize())
                .last(page.isLast())
                .links(PageLinkFactory.getPageLink(page.getTotalPages(), page.getNumber()))
                .build();
    }

    public static class Builder<T> {
        private List<T> contents;
        private int totalPage;
        private long totalCount;
        private int currentPage;
        private boolean last;
        private int pageSize;
        private List<Integer> links;

        private Builder() {}

        public PageInfo<T> build() {
            return new PageInfo<T>(contents, totalPage, totalCount, currentPage, pageSize,last, links);
        }

        public Builder<T> contents(List<T> contents) {
            this.contents = contents;
            return this;
        }

        public Builder<T> totalCount(long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder<T> totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public Builder<T> currentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder<T> last(boolean last) {
            this.last = last;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> links(List<Integer> links) {
            this.links = links;
            return this;
        }
    }

}
