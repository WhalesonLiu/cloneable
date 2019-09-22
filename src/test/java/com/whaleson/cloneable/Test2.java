package com.whaleson.cloneable;

import com.whaleson.cloneable.core.ClonableBeanUtils;
import com.whaleson.cloneable.core.Utils;
import com.whaleson.cloneable.core.UtilsList;
import com.whaleson.cloneable.dto.UserInfoDto;
import com.whaleson.cloneable.entity.AddressInfo;
import com.whaleson.cloneable.entity.UserInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) {

        try{
            HashSet<AddressInfo> addressInfoSet = new HashSet<>();
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

            addressInfos.add(addressInfo);
            addressInfoSet.add(addressInfo);

            AddressInfo addressInfo2 = new AddressInfo();
            addressInfo2.setAddressId("90001");
            addressInfo2.setCountry("China");
            addressInfo2.setCity("Shan Xi");

            addressInfos.add(addressInfo2);
            addressInfoSet.add(addressInfo2);


            userInfo.setAddressInfoHashSet(addressInfoSet);
            userInfo.setAddressInfos(addressInfos);

            UserInfoDto userInfoDto = new UserInfoDto();
            UtilsList.copyPropertiesByReflect(userInfo,userInfoDto);
            System.out.println(userInfoDto);
            if(addressInfoSet instanceof Set){
                System.out.println("set");
            }else{
                System.out.println("no set");
            }
        }catch (Exception e){

        }
    }
}
