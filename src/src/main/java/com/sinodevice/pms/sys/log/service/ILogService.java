package com.sinodevice.pms.sys.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.sys.log.entity.Log;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-10-06
 */
public interface ILogService extends IService<Log> {
    boolean add(Log log);
}
