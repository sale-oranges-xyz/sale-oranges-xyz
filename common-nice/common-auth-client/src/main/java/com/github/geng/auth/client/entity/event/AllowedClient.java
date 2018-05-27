package com.github.geng.auth.client.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author geng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllowedClient {

    private List<String> list;

}
