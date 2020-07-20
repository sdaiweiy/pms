package com.sinodevice.pms.common.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 账号信息
 * </p>
 *
 * @author jobob
 * @since 2018-11-28
 */
@Data
@Accessors(chain = true)
public class Account implements Serializable {
    private Long id;
    private String name;

}
