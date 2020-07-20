package com.sinodevice.pms.common.web;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.sinodevice.pms.common.ErrorCode;
import com.sinodevice.pms.common.spring.SpringHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户登录信息辅助类
 * </p>
 *
 * @author hubin
 * @date 2018-11-06
 */
public class LoginHelper {

  public static Account getAccount() {
    return getAccount(SpringHelper.getCurrentRequest(), true);
  }

  public static Account getAccount(boolean fail) {
    return getAccount(SpringHelper.getCurrentRequest(), fail);
  }

  /**
   * <p>
   * 获取当前登录用户信息
   * </p>
   *
   * @param fail 是否抛出异常
   * @return 用户信息
   */
  public static Account getAccount(HttpServletRequest request, boolean fail) {
    SSOToken st = SSOHelper.getSSOToken(request);
    Assert.fail(fail && null == st, ErrorCode.NOT_LOGIN);
    if(st != null){
      Account Account = new Account();
      Account.setId(Long.valueOf(st.getId()));
      Account.setName(st.getIssuer());
      return Account;
    }else{
      return null;
    }
  }
}
