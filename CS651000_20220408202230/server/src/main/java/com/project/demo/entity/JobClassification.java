package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *职位分类：(JobClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "JobClassification")
public class JobClassification implements Serializable {

    //JobClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_classification_id")
    private Integer job_classification_id;
    // 职位类别
    @Basic
    private String job_category;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
