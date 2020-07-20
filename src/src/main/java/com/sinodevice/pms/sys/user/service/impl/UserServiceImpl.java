package com.sinodevice.pms.sys.user.service.impl;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.captcha.ImageCaptcha;
import com.baomidou.kisso.common.encrypt.MD5Salt;
import com.baomidou.kisso.common.util.RandomType;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sinodevice.pms.common.ErrorCode;
import com.sinodevice.pms.common.FangaoConstant;
import com.sinodevice.pms.common.utils.JacksonUtils;
import com.sinodevice.pms.common.utils.RegexUtils;
import com.sinodevice.pms.sys.log.entity.Log;
import com.sinodevice.pms.sys.log.service.ILogService;
import com.sinodevice.pms.sys.login.controller.DrawController;
import com.sinodevice.pms.sys.login.dto.LoginDTO;
import com.sinodevice.pms.sys.param.entity.Param;
import com.sinodevice.pms.sys.param.service.IParamService;
import com.sinodevice.pms.sys.user.dto.ResetPswDTO;
import com.sinodevice.pms.sys.user.dto.UserDTO;
import com.sinodevice.pms.sys.user.dto.UserInfoDTO;
import com.sinodevice.pms.sys.user.dto.UserResourceDTO;
import com.sinodevice.pms.sys.user.entity.User;
import com.sinodevice.pms.sys.user.entity.UserOrg;
import com.sinodevice.pms.sys.user.entity.UserPost;
import com.sinodevice.pms.sys.user.entity.UserRole;
import com.sinodevice.pms.sys.user.mapper.UserMapper;
import com.sinodevice.pms.sys.user.service.IUserOrgService;
import com.sinodevice.pms.sys.user.service.IUserPostService;
import com.sinodevice.pms.sys.user.service.IUserRoleService;
import com.sinodevice.pms.sys.user.service.IUserService;
import com.sinodevice.pms.sys.user.vo.UserRoleSelectedVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-09-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserOrgService userOrgService;
    @Autowired
    private IUserPostService userPostService;
    @Autowired
    private ILogService logService;
    @Autowired
    private IParamService paramService;

    @Override
    public IPage<User> queryUser(IPage<User> page, User user) {
        if (RegexUtils.isEnglish(user.getRealName())) {
            user.setRealName(null);
        }
        return page.setRecords(this.baseMapper.queryUser(page, user));
    }

    @Override
    public int userNameCount(Long id, String username) {
        if (id != -1) {
            return super.count(Wrappers.<User>query().select("id").ne("id", id).eq("userName", username));
        } else {
            return super.count(Wrappers.<User>query().select("id").eq("userName", username));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveDto(UserDTO dto) {
        User user = dto.convert(User.class);
        user.setSalt(RandomUtil.getText(RandomType.MIX, 8));
        user.setPassword(MD5Salt.md5SaltEncode(user.getUsername() + user.getSalt(), dto.getPassword()));
        return this.save(user) && userRoleService.saveBatch(dto.getRoleIds().stream().map(id -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(id);
            return userRole;
        }).collect(Collectors.toList()))
                && userOrgService.saveBatch(dto.getOrgIds().stream().map(id -> {
            UserOrg userOrg = new UserOrg();
            userOrg.setUserId(user.getId());
            userOrg.setOrgId(id);
            return userOrg;
        }).collect(Collectors.toList()))
                && userPostService.saveBatch(dto.getPostIds().stream().map(id -> {
            UserPost userPost = new UserPost();
            userPost.setUserId(user.getId());
            userPost.setPostId(id);
            return userPost;
        }).collect(Collectors.toList()));
    }

    @Override
    public boolean updateDtoById(UserDTO dto) {
        Assert.fail(null == dto.getId(), ErrorCode.ID_REQUIRED);
        User dbUser = super.getById(dto.getId());
        Assert.fail(null == dbUser, "修改用户不存在");
        /*user.setPassword(MD5Salt.md5SaltEncode(dbUser.getUsername() + dbUser.getSalt(), dto.getPassword()));*/
        return super.updateById(dto.convert(User.class)) && userRoleService.updateByIds(dto.getId(), dto.getRoleIds())
                && userOrgService.updateByIds(dto.getId(), dto.getOrgIds()) && userPostService.updateByIds(dto.getId(), dto.getPostIds());
    }

    @Override
    public User loginByDto(HttpServletRequest request, HttpServletResponse response, LoginDTO dto) {
        if (dto.getCodeFlag())
            Assert.fail(!ImageCaptcha.getInstance().verification(request,
                    DrawController.TICKET, dto.getCode()), "验证码错误");


        Assert.fail(StringUtils.isEmpty(dto.getUsername())
                || StringUtils.isEmpty(dto.getPassword()), "用户名密码不能为空");

        List<User> userList = list(Wrappers.<User>query().eq("username", dto.getUsername()));
        Assert.fail(null == userList || userList.size() != 1, "用户不存在或异常数据");

        User user = userList.get(0);
        Assert.fail(user.getStatus() == -1, "该账号已被禁用");
        Assert.fail(!MD5Salt.md5SaltValid(user.getUsername() + user.getSalt()
                , user.getPassword(), dto.getPassword()), "登录密码错误");

        // Todo 单点登录，踢出前一个相同账号

        // 设置登录 COOKIE
        SSOToken st = new SSOToken();
        st.setId(user.getId());
        st.setIssuer(user.getUsername());
        st.setUserAgent(request);
        SSOHelper.setCookie(request, response, st, true);

        //插入访问日志
        try {
            Log log = new Log();
            log.setUserId(user.getId());
            log.setUsername(user.getUsername());
            log.setUri(request.getRequestURI());
            log.setIp(request.getRemoteAddr());
            log.setParams("login:" + JacksonUtils.toJSONString(request.getParameterMap()));
            log.setRemark("登录");
            logService.add(log);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        // 返回临时对象页面显示
        User tempUser = new User();
        tempUser.setId(user.getId());
        tempUser.setRealName(user.getRealName());
        tempUser.setUsername(user.getUsername());
        List<UserRoleSelectedVO> roleList = userRoleService.listSelectedVO(user.getId());
        if (roleList != null && roleList.size() > 0) {
            for (UserRoleSelectedVO userRoleSelectedVO : roleList) {
                if (userRoleSelectedVO.getSelected() != null && userRoleSelectedVO.getSelected()) {
                    tempUser.setIndexPage(userRoleSelectedVO.getIndexPage());
                    break;
                }
            }
        }
        return tempUser;
    }

    @Override
    public boolean updateById(User user) {
        Assert.fail(null == user.getId(), ErrorCode.ID_REQUIRED);
        return super.updateById(user);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status.intValue() > -1 ? 0 : -1);
        return updateById(user);
    }

    @Override
    public User getById(Serializable id) {
        User user = baseMapper.selectById(id);
        Assert.fail(null == user, ErrorCode.ID_NOT_FOUND);
        return user;
    }

    @Override
    public boolean save(User user) {
        if (null == user) {
            return false;
        }
        user.setStatus(0);
        return super.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id) && userRoleService.removeByUserId(id) && userOrgService.removeByUserId(id);
    }


    @Override
    public boolean unlock(Long id, String password) {
        User user = getById(id);
        return MD5Salt.md5SaltValid(user.getUsername() + user.getSalt()
                , user.getPassword(), password);
    }

    @Override
    public boolean resetPassword(Long id) {
        User user = getById(id);
        Param sysDefaultPassword = paramService.getOne(Wrappers.<Param>query().eq("code", "sys.default.password"));
        String password = (sysDefaultPassword != null ? sysDefaultPassword.getContent() : FangaoConstant.DEFAULT_PASSWORD);
        User temp = new User();
        temp.setId(id);
        temp.setPassword(MD5Salt.md5SaltEncode(user.getUsername() + user.getSalt(), password));
        return super.updateById(temp);
    }

    @Override
    public boolean resetPswOwn(ResetPswDTO dto) {
        Assert.fail(StringUtils.isEmpty(dto.getNewPsw())
                || StringUtils.isEmpty(dto.getOldPsw()), "密码不能为空");

        List<User> userList = list(Wrappers.<User>query().eq("username", dto.getUsername()));
        Assert.fail(null == userList || userList.size() > 1, "用户不存或异常数据");

        User user = userList.get(0);
        Assert.fail(!MD5Salt.md5SaltValid(user.getUsername() + user.getSalt()
                , user.getPassword(), dto.getOldPsw()), "原密码错误");
        User temp = new User();
        temp.setId(user.getId());
        temp.setPassword(MD5Salt.md5SaltEncode(user.getUsername() + user.getSalt(), dto.getNewPsw()));
        return super.updateById(temp);
    }

    @Override
    public List<UserDTO> listReceptionUser() {
        return baseMapper.listReceptionUser();
    }

    @Override
    public boolean queryByUserIdAndResourceId(long userId, long resourceId) {
        List<UserResourceDTO> list = baseMapper.queryByUserIdAndResourceId(userId, resourceId);
        return (list != null && list.size() == 1);
    }

    @Override
    public UserInfoDTO queryUserInfo(long id) {
        List<UserInfoDTO> list = baseMapper.queryUserInfo(id);
        return list.size() == 1 ? list.get(0) : new UserInfoDTO();
    }
}
