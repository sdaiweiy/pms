package com.sinodevice.pms.project.info.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinodevice.pms.common.web.Account;
import com.sinodevice.pms.common.web.LoginHelper;
import com.sinodevice.pms.project.info.dto.ProjectInfoDto;
import com.sinodevice.pms.project.info.entity.ProjectInfo;
import com.sinodevice.pms.project.info.mapper.ProjectInfoMapper;
import com.sinodevice.pms.project.info.service.IProjectInfoService;
import com.sinodevice.pms.project.info.vo.ProjectInfoVo;
import com.sinodevice.pms.project.info.vo.ProjectStatusVo;
import com.sinodevice.pms.project.user.entity.ProjectInfoUser;
import com.sinodevice.pms.project.user.service.IProjectInfoUserService;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdai
 * @since 2020-02-07
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements IProjectInfoService {

    @Autowired
    private IUserPostService userPostService;

    @Autowired
    private IProjectInfoUserService projectInfoUserService;

    @Override
    public IPage<ProjectInfoVo> page(IPage<ProjectInfoVo> page, ProjectInfo projectInfo) {
        return page.setRecords(this.baseMapper.page(page, projectInfo));
    }

    @Override
    public List<ProjectInfo> listByUserId(Long type) {
        Account account = LoginHelper.getAccount();
        return this.baseMapper.listByUserId(account.getId(),type);
    }

    @Override
    public List<ProjectStatusVo> listProjectStatus() {
        LocalDate today = LocalDate.now();
        LocalDate beginDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return this.baseMapper.listProjectStatus(beginDate, endDate);
    }

    @Override
    @Transactional
    public Boolean saveDto(ProjectInfoDto projectInfoDto) {
        boolean result = super.save(projectInfoDto);
        batchInsertProjectInfoUser(projectInfoDto);
        return result;
    }

    @Override
    @Transactional
    public Boolean updateDto(ProjectInfoDto projectInfoDto) {
        boolean result = super.updateById(projectInfoDto);
        projectInfoUserService.remove(Wrappers.<ProjectInfoUser>lambdaQuery().eq(ProjectInfoUser::getProjectId, projectInfoDto.getId()));
        batchInsertProjectInfoUser(projectInfoDto);
        return result;
    }

    private void batchInsertProjectInfoUser(ProjectInfoDto projectInfoDto) {
        List<ProjectInfoUser> projectInfoUserList = new ArrayList<>();
        for (String userId : projectInfoDto.getUserList().split(",")) {
            ProjectInfoUser projectInfoUser = new ProjectInfoUser();
            projectInfoUser.setProjectId(projectInfoDto.getId());
            projectInfoUser.setUserId(Long.valueOf(userId));
            projectInfoUser.setType(0l);
            projectInfoUserList.add(projectInfoUser);
        }
        for (String userId : projectInfoDto.getDeveloperList().split(",")) {
            ProjectInfoUser projectInfoUser = new ProjectInfoUser();
            projectInfoUser.setProjectId(projectInfoDto.getId());
            projectInfoUser.setUserId(Long.valueOf(userId));
            projectInfoUser.setType(1l);
            projectInfoUserList.add(projectInfoUser);
        }
        projectInfoUserService.saveBatch(projectInfoUserList);
    }
}
