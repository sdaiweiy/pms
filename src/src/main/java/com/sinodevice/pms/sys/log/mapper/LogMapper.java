package com.sinodevice.pms.sys.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinodevice.pms.sys.log.entity.Log;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统日志表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-10-06
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
