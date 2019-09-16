package com.whaleson.cloneable.entity;

import com.whaleson.cloneable.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userInfoId;

    private String username;

    private Integer userAge;

    private String account;

    private String password;
}
