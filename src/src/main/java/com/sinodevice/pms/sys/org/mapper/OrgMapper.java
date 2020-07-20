package com.sinodevice.pms.sys.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.org.entity.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统组织表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-11-07
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

    /**
     * <p>
     * 查询所有组织列表
     * </p>
     *
     * @param all
     * @return
     */
    List<Org> listAll(@Param("all") Boolean all);
}
