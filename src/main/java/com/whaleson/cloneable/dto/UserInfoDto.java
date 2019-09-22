package com.whaleson.cloneable.dto;

import com.whaleson.cloneable.entity.AddressInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserInfoDto {
    private String userInfoId;

    private String username;

    private Integer userAge;

    private String account;

    //private AddressInfoDto address;

    private ArrayList<AddressInfoDto> addressInfos;

    private HashSet<AddressInfoDto> addressInfoHashSet;
}
