package com.sinodevice.pms.sys.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.dict.entity.DictCommon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 通用数据字典  Mapper 接口
 * </p>
 *
 * @author wdai
 * @since 2019-05-23
 */
@Mapper
public interface DictCommonMapper extends BaseMapper<DictCommon> {

    /**
     * @return
     */
    List<DictCommon> selectTopList();

}
