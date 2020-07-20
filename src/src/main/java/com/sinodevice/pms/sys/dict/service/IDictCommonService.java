package com.sinodevice.pms.sys.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.dict.entity.DictCommon;

import java.util.List;

/**
 * <p>
 * 通用数据字典  服务类
 * </p>
 *
 * @author wdai
 * @since 2019-05-23
 */
public interface IDictCommonService extends IService<DictCommon> {

    public List<DictCommon> selectTopList();

    /***
     * 根据父节点的Code编码,查询所有的子节点
     * @param parentCode
     * @return
     */
    public List<DictCommon> listByParentCode(String parentCode);

}
