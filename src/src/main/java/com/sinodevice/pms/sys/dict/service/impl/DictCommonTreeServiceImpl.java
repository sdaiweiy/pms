package com.sinodevice.pms.sys.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.sys.dict.entity.DictCommonTree;
import com.sinodevice.pms.sys.dict.mapper.DictCommonTreeMapper;
import com.sinodevice.pms.sys.dict.service.IDictCommonTreeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用-树键值对字典  服务实现类
 * </p>
 *
 * @author wdai
 * @since 2019-05-24
 */
@Service
public class DictCommonTreeServiceImpl extends ServiceImpl<DictCommonTreeMapper, DictCommonTree> implements IDictCommonTreeService {

    @Override
    public boolean updateStatus(Long id, Integer status) {
        DictCommonTree dictCommonTree = new DictCommonTree();
        dictCommonTree.setId(id);
        dictCommonTree.setStatus(status);
        return updateById(dictCommonTree);
    }

    @Override
    public boolean save(DictCommonTree dictCommonTree) {
        dictCommonTree.setStatus(0);
        return super.save(dictCommonTree.initialName());
    }

    @Override
    public boolean updateById(DictCommonTree dictCommonTree) {
        return super.updateById(dictCommonTree.initialName());
    }

}
