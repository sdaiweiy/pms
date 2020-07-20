package com.sinodevice.pms.sys.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.utils.RegexUtils;
import com.sinodevice.pms.sys.dict.entity.DictCommonPlain;
import com.sinodevice.pms.sys.dict.mapper.DictCommonPlainMapper;
import com.sinodevice.pms.sys.dict.service.IDictCommonPlainService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 列表型字典  服务实现类
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
@Service
public class DictCommonPlainServiceImpl extends ServiceImpl<DictCommonPlainMapper, DictCommonPlain> implements IDictCommonPlainService {

    @Override
    public IPage<DictCommonPlain> page(Page page, DictCommonPlain dictCommonPlain) {
        QueryWrapper<DictCommonPlain> qw = new QueryWrapper();
        // 首字母查询
        if (RegexUtils.isEnglish(dictCommonPlain.getName())) {
            dictCommonPlain.setInitial(dictCommonPlain.getName());
            dictCommonPlain.setName(null);
        }
        qw.setEntity(dictCommonPlain);
        return super.page(page, qw);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        DictCommonPlain dictCommonPlain = new DictCommonPlain();
        dictCommonPlain.setId(id);
        dictCommonPlain.setStatus(status);
        return updateById(dictCommonPlain);
    }

    @Override
    public boolean save(DictCommonPlain dictCommonPlain) {
        dictCommonPlain.setStatus(0);
        return super.save(dictCommonPlain.initialName());
    }

    @Override
    public boolean updateById(DictCommonPlain dictCommonPlain) {
        return super.updateById(dictCommonPlain.initialName());
    }
}
