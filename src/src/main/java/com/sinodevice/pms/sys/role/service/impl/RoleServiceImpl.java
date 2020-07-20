package com.sinodevice.pms.sys.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.utils.RegexUtils;
import com.sinodevice.pms.sys.role.entity.Role;
import com.sinodevice.pms.sys.role.mapper.RoleMapper;
import com.sinodevice.pms.sys.role.service.IRoleService;
import com.sinodevice.pms.sys.user.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-09-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public IPage<Role> page(Page page, Role role) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        if (RegexUtils.isEnglish(role.getName())) {
            role.setInitial(role.getName());
            role.setName(null);
        }
        qw.setEntity(role);
        qw.orderByAsc("SORT");
        return super.page(page, qw);
    }

    @Override
    public List<Role> listAll() {
        return super.list(Wrappers.<Role>query().select("id", "name")
                .eq("status", 0).orderByAsc("sort"));
    }

    @Override
    public boolean save(Role role) {
        if (null == role) {
            return false;
        }
        return super.save(role.initialName());
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        Role role = new Role();
        role.setId(id);
        role.setStatus(status.intValue() > -1 ? 0 : -1);
        return updateById(role);
    }

    @Override
    public boolean removeById(Serializable id) {
        Assert.fail(userRoleService.relation(id), "存在用户关联不允许删除");
        return super.removeById(id);
    }
}
