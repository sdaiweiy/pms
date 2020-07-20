package com.sinodevice.pms.core.log;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.sinodevice.pms.sys.log.entity.Log;

import java.util.concurrent.ThreadFactory;

/**
 * <p>
 * lmax disruptor
 * </p>
 *
 * @author jobob
 * @since 2018-10-07
 */
public class LogDisruptor {

    /**
     * 当前对象静态实例
     */
    public static final LogDisruptor INSTANCE = new LogDisruptor();

    /**
     * 缓冲区大小
     */
    private static final int RING_BUFFER_SIZE = 256 * 1024;
    private static final int HALF_A_SECOND = 500;
    private static final int MAX_DRAIN_ATTEMPTS_BEFORE_SHUTDOWN = 20;

    /**
     * ProducerType 有 single 和 multi 之分，分别对应不同的 RingBuffer 实现，性能不同
     * WaitStrategy 对应消费者阻塞时的处理策略
     */
    Disruptor<LogEvent> disruptor = new Disruptor<>(() -> new LogEvent(), LogDisruptor.RING_BUFFER_SIZE,
            (ThreadFactory) Thread::new, ProducerType.MULTI, new SleepingWaitStrategy());

    final LogEventHandler handler = new LogEventHandler();

    private RingBuffer<LogEvent> ringBuffer;

    public void start() {
        disruptor.handleEventsWith(handler);
        ringBuffer = disruptor.start();
    }

    public void stop() {
        try {
            // OK
            disruptor.shutdown();

            // wait up to 10 seconds for the ringbuffer to drain
            RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
            for (int i = 0; i < LogDisruptor.MAX_DRAIN_ATTEMPTS_BEFORE_SHUTDOWN; i++) {
                if (ringBuffer.hasAvailableCapacity(ringBuffer.getBufferSize())) {
                    break;
                }
                try {
                    // give ringbuffer some time to drain...
                    Thread.sleep(LogDisruptor.HALF_A_SECOND);
                } catch (InterruptedException e) {
                    // ignored
                }
            }
            disruptor.shutdown();
            disruptor = null;
        } catch (Exception e) {
            // to do nothing
        }
    }

    /**
     * 推送访问日志
     */
    public void publish(Log Log) {
        if (null != Log) {
            long seq = ringBuffer.next();
            try {
                LogEvent valueEvent = ringBuffer.get(seq);
                valueEvent.setLog(Log);
            } finally {
                ringBuffer.publish(seq);
            }
        }
    }
}
