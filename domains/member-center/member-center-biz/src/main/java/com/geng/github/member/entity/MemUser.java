package com.geng.github.member.entity;

import com.github.geng.constant.DataConstant;
import com.github.geng.entity.BaseLongIdTimeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统用户
 * @author geng
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "mem_user")
public class MemUser extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private String mobile;                      // 手机号
    private int status = DataConstant.ENABLE;   // 状态
    private Long loginTime;                     // 最近一次登录时间
    private String nickName;                    // 会员昵称
    private String avatar;                      // 会员头像
    private Long point;                         // 积分
    private Long deposit;                       // 余额,单位分
    private String email;                       // 邮箱
    private String password;                    // 密码
    private String wxOpenId;                    // 微信open id
    private String aliOpenId;                   // 支付宝open id
    private String country;                     // 所属国家
    private String province;                    // 所属省
    private String city;                        // 所属城市
    private String area;                        // 所属地区
    private String address;                     // 详细地址
    private String fromWhere;                   // 来源
    // ----------------------------------------------------
    // constructors
    public MemUser (Long id) {
        super(id);
    }

}
