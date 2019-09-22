package com.whaleson.cloneable.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userInfoId;

    private String username;

    private Integer userAge;

    private String account;

    private String password;

    //private AddressInfo address;

    private Array arrays;

    private ArrayList<AddressInfo> addressInfos;

    private HashSet<AddressInfo> addressInfoHashSet;

}
