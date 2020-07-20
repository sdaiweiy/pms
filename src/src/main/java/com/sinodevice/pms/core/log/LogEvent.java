package com.sinodevice.pms.core.log;

import com.sinodevice.pms.sys.log.entity.Log;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * lmax disruptor Event
 * </p>
 *
 * @author jobob
 * @since 2018-10-07
 */
@Data
@Accessors(chain = true)
public class LogEvent implements Serializable {

    private Log log;

}
