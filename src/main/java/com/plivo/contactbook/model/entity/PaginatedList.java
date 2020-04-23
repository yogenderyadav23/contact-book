package com.plivo.contactbook.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PaginatedList<T> implements Serializable {

    private static final long serialVersionUID = 6232539024161138867L;
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
