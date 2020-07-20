package com.sinodevice.pms.sys.post.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.post.entity.Post;

/**
 * <p>
 * 系统岗位表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-10-21
 */
public interface IPostService extends IService<Post> {


    /**
     * <p>
     * 查询岗位信息分页
     * </p>
     *
     * @param page 分页对象
     * @param post 岗位信息
     * @return
     */
    IPage<Post> page(Page page, Post post);


    /**
     * <p>
     * 更新用户状态
     * </p>
     *
     * @param id     用户 ID
     * @param status 状态
     * @return
     */
    boolean updateStatus(Long id, Integer status);
}
