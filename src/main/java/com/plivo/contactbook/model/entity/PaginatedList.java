package com.plivo.contactbook.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedList<T> {

    private Long totalPages;
    private Long pageNo;
    private Long pageSize;
    private List<T> list;


    public PaginatedList(Long totalPages, Long pageNo, Long pageSize, List<T> list) {
        this.totalPages = totalPages;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.list = list;
    }



}
