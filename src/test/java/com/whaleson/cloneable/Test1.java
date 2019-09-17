package com.whaleson.cloneable;

import com.whaleson.cloneable.core.ClonableBeanUtils;
import com.whaleson.cloneable.dto.UserInfoDto;
import com.whaleson.cloneable.entity.AddressInfo;
import com.whaleson.cloneable.entity.UserInfo;

public class Test1 {


    public static void main(String[] args) {
        try{
            UserInfo userInfo = new UserInfo();
            userInfo.setUserInfoId("1");
            userInfo.setUserAge(20);
            userInfo.setAccount("1138765678");
            userInfo.setUsername("Whaleson");
            userInfo.setPassword("demo");

            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setAddressId("10001");
            addressInfo.setCountry("China");
            addressInfo.setCity("Shanghai");
            userInfo.setAddress(addressInfo);

            UserInfoDto userInfoDto = new UserInfoDto();
            ClonableBeanUtils.copyPropertiesByIntrospector(userInfo,userInfoDto);
            System.out.println(userInfoDto.getAddress().getCity());
        }catch (Exception e){

        }
    }
}
