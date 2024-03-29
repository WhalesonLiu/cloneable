package com.whaleson.cloneable;

import com.whaleson.cloneable.core.ClonableBeanUtils;
import com.whaleson.cloneable.dto.UserInfoDto;
import com.whaleson.cloneable.entity.AddressInfo;
import com.whaleson.cloneable.entity.UserInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test1 {


    public static void main(String[] args) {
        try{
            Set<AddressInfo> addressInfoSet = new HashSet<>();
            ArrayList<AddressInfo> addressInfos = new ArrayList<>();



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
            addressInfoSet.add(addressInfo);
            addressInfos.add(addressInfo);

            userInfo.setAddressInfos(addressInfos);
            //userInfo.setAddress(addressInfo);

            UserInfoDto userInfoDto = new UserInfoDto();
            ClonableBeanUtils.copyPropertiesByReflect(userInfo,userInfoDto);
            System.out.println(userInfoDto);
        }catch (Exception e){

        }
    }
}
