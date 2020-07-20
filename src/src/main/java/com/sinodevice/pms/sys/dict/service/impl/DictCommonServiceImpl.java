package com.sinodevice.pms.sys.dict.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.sys.dict.entity.DictCommon;
import com.sinodevice.pms.sys.dict.mapper.DictCommonMapper;
import com.sinodevice.pms.sys.dict.service.IDictCommonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用数据字典  服务实现类
 * </p>
 *
 * @author wdai
 * @since 2019-05-23
 */
@Service
public class DictCommonServiceImpl extends ServiceImpl<DictCommonMapper, DictCommon> implements IDictCommonService {

    @Override
    public List<DictCommon> selectTopList() {
        return baseMapper.selectTopList();
    }

    @Override
    public List<DictCommon> listByParentCode(String parentCode) {
        DictCommon parent = super.getOne(Wrappers.<DictCommon>query().eq("code", parentCode));

        DictCommon condition = new DictCommon();
        condition.setPid(parent.getId());
        return this.list(Wrappers.query(condition));
    }

}
