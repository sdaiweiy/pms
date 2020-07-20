package com.sinodevice.pms.sys.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.dict.entity.DictCommonPlain;

/**
 * <p>
 * 列表型字典  服务类
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
public interface IDictCommonPlainService extends IService<DictCommonPlain> {
    /**
     * <p>
     * 查询字典信息分页
     * </p>
     *
     * @param page            分页对象
     * @param dictCommonPlain 字典信息
     * @return
     */
    IPage<DictCommonPlain> page(Page page, DictCommonPlain dictCommonPlain);

    /**
     * <p>
     * 更新字典状态
     * </p>
     *
     * @param id     字典 ID
     * @param status 状态
     * @return
     */
    boolean updateStatus(Long id, Integer status);
}
