package com.whaleson.cloneable;

import com.whaleson.cloneable.dto.UserInfoDto;
import com.whaleson.cloneable.entity.UserInfo;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Test1 {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId("1");
        userInfo.setUserAge(20);
        userInfo.setAccount("1138765678");
        userInfo.setUsername("Whaleson");
        userInfo.setPassword("demo");

        UserInfoDto userInfoDto = new UserInfoDto();
        //ClonableBeanUtils.copyPropertiesByIntrospector(userInfo,userInfoDto);
        System.out.println(userInfoDto.getAccount());


        /*BeanUtils.copyProperties(userInfo,userInfoDto);
        System.out.println(userInfoDto.getAccount());
        Class clazz = userInfo.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method method = propertyDescriptor.getReadMethod();
            Object value = method.invoke(userInfo);
            System.out.print(propertyDescriptor.getName());
            System.out.print("\t\t" + propertyDescriptor.getPropertyType());
            System.out.print("\t\t" + propertyDescriptor.getDisplayName());
            System.out.print("\t\t" + value);
            System.out.println();

        }*/

        //beanDescriptor.get

        List<String> stringList = new ArrayList<>();
        if ( stringList == null){
            System.out.println("null");
        }else {
            System.out.println("is empty");
        }
        System.out.println(stringList);
        for (String item: stringList) {
            System.out.println("item\t" + item.toLowerCase());
        }
    }
}
