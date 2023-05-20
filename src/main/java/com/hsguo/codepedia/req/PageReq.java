package com.hsguo.codepedia.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PageReq {
    @NotNull(message = "The page number cannot be null")
    private int page;

    @NotNull(message = "The number of items on each page cannot be null")
    @Max(value = 1000, message = "The number of items on each page cannot be more than 1000")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}