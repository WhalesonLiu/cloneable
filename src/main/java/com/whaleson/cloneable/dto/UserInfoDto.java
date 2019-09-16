package com.whaleson.cloneable.dto;

import lombok.Data;

@Data

public class UserInfoDto {
    private String userInfoId;

    private String username;

    private Integer userAge;

    private String account;

    private AddressInfoDto address;
}
