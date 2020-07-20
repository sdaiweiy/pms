package com.sinodevice.pms.sys.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.ErrorCode;
import com.sinodevice.pms.common.utils.RegexUtils;
import com.sinodevice.pms.sys.post.entity.Post;
import com.sinodevice.pms.sys.post.mapper.PostMapper;
import com.sinodevice.pms.sys.post.service.IPostService;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 系统岗位表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-10-21
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Autowired
    @Lazy
    private IUserPostService userPostService;

    @Override
    public IPage<Post> page(Page page, Post post) {
        QueryWrapper<Post> qw = new QueryWrapper<>();
        if (RegexUtils.isEnglish(post.getName())) {
            post.setInitial(post.getName());
            post.setName(null);
        }
        qw.setEntity(post);
        return super.page(page, qw);
    }

    @Override
    public boolean save(Post post) {
        if (null == post) {
            return false;
        }
        return super.save(post.initialName());
    }

    @Override
    public boolean updateById(Post post) {
        Assert.fail(null == post.getId(), ErrorCode.ID_REQUIRED);
        return super.updateById(post);
    }


    @Override
    public boolean updateStatus(Long id, Integer status) {
        Post post = new Post();
        post.setId(id);
        post.setStatus(status);
        return updateById(post);
    }

    @Override
    public boolean removeById(Serializable id) {
        Assert.fail(userPostService.relation(id), "存在用户关联不允许删除");
        return super.removeById(id);
    }
}
