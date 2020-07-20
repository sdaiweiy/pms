package com.sinodevice.pms.sys.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.dict.entity.DictCommonTree;

/**
 * <p>
 * 通用-树键值对字典  服务类
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
public interface IDictCommonTreeService extends IService<DictCommonTree> {

    boolean updateStatus(Long id, Integer status);

}
