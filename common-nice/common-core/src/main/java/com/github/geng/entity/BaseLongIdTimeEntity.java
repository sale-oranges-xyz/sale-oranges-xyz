package com.github.geng.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * 具有创建时间与修改时间的实体类父类
 * @author geng
 */
@Setter
@Getter
@MappedSuperclass
public class BaseLongIdTimeEntity extends BaseLongIdEntity {
    private static final long serialVersionUID = 1L;

    private Long createTime;
    private Long modifyTime;

}
