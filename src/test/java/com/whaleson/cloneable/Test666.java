package com.whaleson.cloneable;

import com.whaleson.cloneable.entity.AddressInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test666 {

    public static void main(String[] args) {

        TestEntity testEntity = new TestEntity();

        List<AddressInfo> addressInfos = new ArrayList<>();

        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddressId("10001");
        addressInfo.setCountry("China");
        addressInfo.setCity("Shanghai");

        addressInfos.add(addressInfo);

        //testEntity.setAddressInfoArrayList(addressInfos);
        testEntity.setAddressInfoList(addressInfos);





    }
}
