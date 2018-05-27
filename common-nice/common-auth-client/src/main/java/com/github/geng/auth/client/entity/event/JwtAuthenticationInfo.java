package com.github.geng.auth.client.entity.event;

import com.github.geng.token.response.JwtAuthenticationResponse;
import lombok.Data;

/**
 * @author geng
 */
@Data
public class JwtAuthenticationInfo extends JwtAuthenticationResponse {
    private boolean isNew = true;              // 是否新产生的token

    // --------------------------------------------------------
    // constructors
    public JwtAuthenticationInfo(String token) {
        super(token);
    }

    public JwtAuthenticationInfo(String token, boolean isNew) {
        super(token);
        this.isNew = isNew;
    }

}
