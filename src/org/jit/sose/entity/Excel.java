package org.jit.sose.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Excel {
    private Integer id;

    private Integer userId;

    private String title;

    private String state;

    private Date createdDate;

    private Date stateDate;

   
}