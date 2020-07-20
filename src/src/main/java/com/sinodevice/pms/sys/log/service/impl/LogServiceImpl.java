package com.sinodevice.pms.sys.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.sys.log.entity.Log;
import com.sinodevice.pms.sys.log.mapper.LogMapper;
import com.sinodevice.pms.sys.log.service.ILogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-10-06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    @Override
    public boolean add(Log log) {
        if (null == log) {
            return false;
        }
        log.setCreateTime(LocalDateTime.now());
        return super.save(log);
    }
}
