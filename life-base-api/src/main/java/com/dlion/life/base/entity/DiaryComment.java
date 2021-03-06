package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Data
public class DiaryComment {

    private Integer id;

    private Integer diaryId;

    private Integer pid;

    /**
     * 评论者id
     */
    private Integer reviewerId;

    /**
     * 被评论者id
     */
    private Integer respondentId;

    private String textComment;

    private String soundComment;

    private Date createTime;
}