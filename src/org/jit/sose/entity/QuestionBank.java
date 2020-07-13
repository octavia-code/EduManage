package org.jit.sose.entity;

import java.util.Date;

import lombok.Data;
@Data
public class QuestionBank {
    private Integer id;

    private String subject;

    private String chapter;

    private String stem;

    private String answer;

    private String state;

    private Date createdDate;

}