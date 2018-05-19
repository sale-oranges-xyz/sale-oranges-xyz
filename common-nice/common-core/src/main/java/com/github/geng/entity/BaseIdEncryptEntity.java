package com.github.geng.entity;

import com.github.geng.util.IdEncryptUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author geng
 */
@MappedSuperclass
@Setter
@Getter
public class BaseIdEncryptEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    // ==========================================================
    // constructor
    public BaseIdEncryptEntity (BaseLongIdEntity baseLongIdEntity) {
        this.id = IdEncryptUtils.encode(baseLongIdEntity.getId());
    }
    public BaseIdEncryptEntity () {

    }

}
