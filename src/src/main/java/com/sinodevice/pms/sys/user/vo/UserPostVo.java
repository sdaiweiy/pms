package com.sinodevice.pms.sys.user.vo;

import com.sinodevice.pms.sys.user.entity.UserPost;
import lombok.Data;

@Data
public class UserPostVo extends UserPost {

    private String postName;

    private String userName;

}
