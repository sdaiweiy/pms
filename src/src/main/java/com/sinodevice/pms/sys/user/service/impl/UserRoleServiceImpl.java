package com.sinodevice.pms.sys.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.sys.user.entity.UserRole;
import com.sinodevice.pms.sys.user.mapper.UserRoleMapper;
import com.sinodevice.pms.sys.user.service.IUserRoleService;
import com.sinodevice.pms.sys.user.vo.UserRoleSelectedVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户角色关联表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


    @Override
    public List<UserRoleSelectedVO> listSelectedVO(Long userId) {
        return baseMapper.selectSelectedVO(userId);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateByIds(Long userId, List<Long> ids) {
        Assert.fail(null == userId || CollectionUtils.isEmpty(ids), "用户角色 ID 不能为空");
        return removeByUserId(userId) && saveBatch(ids.stream().map(id -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(id);
            return userRole;
        }).collect(Collectors.toList()));
    }


    @Override
    public boolean removeByUserId(Serializable userId) {
        return super.remove(Wrappers.<UserRole>query().eq("user_id", userId));
    }

    @Override
    public boolean relation(Serializable roleId) {
        return count(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId,
                roleId)) > 0;
    }
}
