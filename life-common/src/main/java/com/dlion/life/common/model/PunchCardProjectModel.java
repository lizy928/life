package com.dlion.life.common.model;

import lombok.Data;

/**
 * 打卡项目
 *
 * @author 李正元
 * @date 2019/9/11
 */
@Data
public class PunchCardProjectModel {

    private Integer id;

    private String projectName;

    private Integer privacyType;

    private Integer openAudit;

    private String typeLabel;

    private String coverImgUrl;

    private Integer creatorId;

    private String creatorIntroduce;

    private String weixinNum;

    private Integer allPunchCardNum;

    private Integer todayPunchCardNum;

    private Integer attendUserNum;

}
