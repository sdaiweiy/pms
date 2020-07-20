package com.sinodevice.pms.sys.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.resource.dto.RoleResourceDTO;
import com.sinodevice.pms.sys.role.entity.RoleResource;
import com.sinodevice.pms.sys.role.vo.ResourceZTreeVO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统角色资源关联表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-09-24
 */
public interface IRoleResourceService extends IService<RoleResource> {

    /**
     * <p>
     * 查询 ZTree VO 列表
     * </p>
     *
     * @param roleId 角色 ID
     * @return
     */
    List<ResourceZTreeVO> listZTreeVO(Long roleId);

    /**
     * <p>
     * 保存资源 DTO
     * </p>
     *
     * @param dto 资源 DTO
     * @return
     */
    boolean saveByDto(RoleResourceDTO dto);

    /**
     * <p>
     * 是否存在资源关联
     * </p>
     *
     * @param resourceId 资源 ID
     * @return
     */
    boolean relation(Serializable resourceId);
}
