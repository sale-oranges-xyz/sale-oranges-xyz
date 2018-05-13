package com.github.geng.entity;


import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author geng
 */
@MappedSuperclass
public class BaseLongIdEntity extends BaseIdEntity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 判断是否新增数据
     * @return
     */
    @Transient
    public boolean isNewRaw() {
        if (null == super.getId() || super.getId() == -1L) {
            return true;
        }
        return false;
    }

}
