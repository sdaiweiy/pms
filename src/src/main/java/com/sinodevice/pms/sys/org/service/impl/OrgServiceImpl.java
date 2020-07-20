package com.sinodevice.pms.sys.org.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.sys.org.entity.Org;
import com.sinodevice.pms.sys.org.mapper.OrgMapper;
import com.sinodevice.pms.sys.org.service.IOrgService;
import com.sinodevice.pms.sys.user.service.IUserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统组织表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-07
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

    @Autowired
    private IUserOrgService userOrgService;

    @Override
    public List<Org> listAll(Boolean all) {
        return baseMapper.listAll(all);
    }

    @Override
    public Integer childNode(Serializable id) {
        return count(Wrappers.<Org>lambdaQuery().eq(Org::getPid,
                id).eq(Org::getDeleted, 0));
    }

    @Override
    public boolean save(Org org) {
        if (null == org) {
            return false;
        }
        return super.save(org);
    }

    @Override
    public boolean removeById(Serializable id) {
        Assert.fail(this.childNode(id) > 0, "存在子节点不允许删除");
        Assert.fail(userOrgService.relation(id), "存在用户关联不允许删除");
        return super.removeById(id);
    }
}
