package com.sinodevice.pms.sys.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.sys.user.entity.UserPost;
import com.sinodevice.pms.sys.user.mapper.UserPostMapper;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import com.sinodevice.pms.sys.user.vo.UserPostVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements IUserPostService {

    @Override
    public List<UserPostVo> listPostByUserId(Long userId) {
        return baseMapper.listPostByUserId(userId);
    }

    @Override
    public boolean removeByUserId(Serializable userId) {
        return super.remove(Wrappers.<UserPost>query().eq("user_id", userId));
    }

    @Override
    public boolean relation(Serializable postId) {
        return count(Wrappers.<UserPost>lambdaQuery().eq(UserPost::getPostId,
                postId)) > 0;
    }

    @Override
    public boolean updateByIds(Long userId, List<Long> ids) {
        //Assert.fail(null == userId || CollectionUtils.isEmpty(ids), "岗位 ID 不能为空");
        return removeByUserId(userId) && saveBatch(ids.stream().map(id -> {
            UserPost userPost = new UserPost();
            userPost.setUserId(userId);
            userPost.setPostId(id);
            return userPost;
        }).collect(Collectors.toList()));
    }

    @Override
    public boolean existPost(Long userId, String postCode) {
        return this.baseMapper.existPost(userId, postCode) > 0;
    }

}
