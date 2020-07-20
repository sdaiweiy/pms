package com.sinodevice.pms.project.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinodevice.pms.project.task.entity.ProjectTaskProcess;
import com.sinodevice.pms.project.task.vo.ProjectTaskProcessVo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
public interface IProjectTaskProcessService extends IService<ProjectTaskProcess> {

    /***
     * 开始任务
     * @param taskId
     */
    Boolean startTask(Long taskId, LocalDateTime time);

    /***
     * 暂停任务
     * @param taskId
     * @return
     */
    Boolean pauseTask(Long taskId, LocalDateTime time);

    /***
     * 继续任务
     * @param taskId
     * @return
     */
    Boolean continueTask(Long taskId, LocalDateTime time);

    Boolean finishTask(ProjectTaskProcess projectTaskProcess);

    List<ProjectTaskProcessVo> listLoginUserWeekTask();

    /***
     * 获取任务工作总时长
     * @param taskId
     * @return
     */
    BigDecimal getTaskWorkHour(Long taskId);

    /***
     * 获取最后一次任务的开始时间
     * @return
     */
    LocalDateTime getLastBeginTime(Long taskId);

}
