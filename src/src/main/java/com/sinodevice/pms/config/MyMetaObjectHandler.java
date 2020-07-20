package com.sinodevice.pms.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * <p>
 * 填充器
 * </p>
 *
 * @author jobob
 * @since 2018-11-01
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Account account = LoginHelper.getAccount(false);
        if (null != account) {
            setFieldValByName("createBy", account.getId(), metaObject);
        }
        setFieldValByName("deleted", false, metaObject);
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Account account = LoginHelper.getAccount(false);
        if (null != account) {
            setFieldValByName("updateBy", account.getId(), metaObject);
        }
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}