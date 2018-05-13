package com.github.geng.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 可以自定义数据类型的实体超类
 * @author geng
 */
@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
public class BaseIdEntity<ID> implements Serializable {
    private static final long serialVersionUID = 6872044216589327224L;

    @Id
    private ID id;                  //主键

    ///////////////////////////////////constructors
    public BaseIdEntity(ID id) {
        this.id = id;
    }

}
