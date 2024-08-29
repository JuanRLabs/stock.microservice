package com.microservice.stockmicroservice.domain.util.Pagination;

import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.util.DomainConstants;

public class PageableRequest {
    private int page;
    private int size;
    private String sort;
    private Sorted sorted;
    private final int MAX_PER_PAGE = 50;

    public PageableRequest(int page, int size, String sort, Sorted sorted) {
        if (page < 0 || size < 0 ) throw new IllegalArgumentException(DomainConstants.FIELD_PAGE_OR_SIZE_ILLEGAL_ARGUMENT_MESSAGE);
        if (sort.isEmpty()) sort = "id";
        if ("DESC".equalsIgnoreCase(sorted.name()) || sorted.name().isEmpty()) {
            sorted = Sorted.DESC;
        }
        this.page = page;
        this.size = ( size > 50 ? MAX_PER_PAGE: size ) ;
        this.sort = sort;
        this.sorted = sorted;
    }

    public int getPage() {return page;}

    public void setPage(int page) {this.page = page;}

    public int getSize() {return size;}

    public void setSize(int size) {this.size = size;}

    public String getSort() {return sort;}

    public void setSort(String sort) {this.sort = sort;}

    public Sorted getSorted() {return sorted;}

    public void setSorted(Sorted sorted) {this.sorted = sorted;}
}
