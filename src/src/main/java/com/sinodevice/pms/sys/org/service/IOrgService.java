package com.sinodevice.pms.sys.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.org.entity.Org;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统组织表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-07
 */
public interface IOrgService extends IService<Org> {

    /**
     * <p>
     * 查询指定顶级组织
     * </p>
     *
     * @return
     */
    List<Org> listAll(Boolean all);

    /**
     * <p>
     * 查询 ID 子节点数
     * </p>
     *
     * @param id 主键 ID
     * @return
     */
    Integer childNode(Serializable id);

}
