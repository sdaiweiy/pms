package com.sinodevice.pms.core.log;

import com.lmax.disruptor.EventHandler;
import com.sinodevice.pms.common.spring.SpringHelper;
import com.sinodevice.pms.sys.log.service.ILogService;

/**
 * <p>
 * lmax disruptor EventHandler
 * </p>
 *
 * @author jobob
 * @since 2018-10-07
 */
public class LogEventHandler implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        ILogService logService = SpringHelper.getBean(ILogService.class);
        logService.save(event.getLog());
    }
}
