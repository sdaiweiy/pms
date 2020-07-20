package com.sinodevice.pms.sys.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.role.entity.Role;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-09-15
 */
public interface IRoleService extends IService<Role> {


    IPage<Role> page(Page page, Role role);


    /**
     * 查询所有正常角色信息
     */
    List<Role> listAll();


    /**
     * <p>
     * 修改角色状态
     * </p>
     *
     * @param id     角色 ID
     * @param status 状态
     * @return
     */
    boolean updateStatus(Long id, Integer status);

}
