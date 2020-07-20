package com.sinodevice.pms.sys.param.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.ErrorCode;
import com.sinodevice.pms.common.utils.RegexUtils;
import com.sinodevice.pms.sys.param.entity.Param;
import com.sinodevice.pms.sys.param.mapper.ParamMapper;
import com.sinodevice.pms.sys.param.service.IParamService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统参数表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-10-20
 */
@Service
public class ParamServiceImpl extends ServiceImpl<ParamMapper, Param> implements IParamService {

    @Override
    public IPage<Param> page(Page page, Param param) {
        QueryWrapper<Param> qw = new QueryWrapper();
        // 首字母查询
        if (RegexUtils.isEnglish(param.getName())) {
            param.setInitial(param.getName());
            param.setName(null);
        }
        qw.setEntity(param);
        return super.page(page, qw);
    }

    @Override
    public boolean save(Param param) {
        int count = count(Wrappers.<Param>query().eq("code", param.getCode()));
        Assert.fail(count > 0, "该编码已存在，请填写其他编码");
        if (null == param) {
            return false;
        }
        return super.save(param.initialName());
    }

    @Override
    public boolean updateById(Param param) {
        Assert.fail(null == param.getId(), ErrorCode.ID_REQUIRED);
        int count = count(Wrappers.<Param>query().eq("code", param.getCode()).ne("id", param.getId()));
        Assert.fail(count > 0, "该编码已存在，请填写其他编码");
        return super.updateById(param);
    }

    public Param getByCode(String code) {
        return this.getOne(Wrappers.<Param>lambdaQuery().eq(Param::getCode, code));
    }


    @Override
    public boolean updateSys(Long id, Integer sys) {
        Param param = new Param();
        param.setId(id);
        param.setSys(sys);
        return updateById(param);
    }
}
