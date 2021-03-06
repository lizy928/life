package com.dlion.life.common.model;

import lombok.Data;

import java.util.Date;

/**
 * 打卡日历评论
 *
 * @author 李正元
 * @date 2019/9/11
 */
@Data
public class DiaryCommentModel {

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
