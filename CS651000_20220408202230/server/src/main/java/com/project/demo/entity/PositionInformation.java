package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *职位信息：(PositionInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PositionInformation")
public class PositionInformation implements Serializable {

    //PositionInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_information_id")
    private Integer position_information_id;
    // 职位名称
    @Basic
    private String job_title;
    // 职位类别
    @Basic
    private String job_category;
    // 封面
    @Basic
    private String cover;
    // 招聘人数
    @Basic
    private Integer number_of_recruits;
    // 招聘公司
    @Basic
    private String recruitment_company;
    // 简历投递地址
    @Basic
    private String resume_delivery_address;
    // 职位要求
    @Basic
    private String job_requirements;
    // 薪资待遇
    @Basic
    private String salary;
    // 详情
    @Basic
    private String details;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
