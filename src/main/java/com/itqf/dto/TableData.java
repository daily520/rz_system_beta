package com.itqf.dto;

import lombok.Data;

import java.util.List;


@Data
public class TableData {

    private  long total;
    private List<?> rows;


}
