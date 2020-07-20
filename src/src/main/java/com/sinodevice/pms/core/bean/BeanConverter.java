package com.sinodevice.pms.core.bean;

import jodd.bean.BeanCopy;

import java.io.Serializable;

public class BeanConverter implements Serializable {
    private static final long serialVersionUID = 1L;

    public BeanConverter() {
    }

    public <T> T convert(Class<T> obj) {
        try {
            T bean = obj.newInstance();
            BeanCopy.beans(this, bean).copy();
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("转换对象失败", e);
        }
    }
}