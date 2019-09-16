package com.whaleson.cloneable.core;


import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClonableBeanUtils {

    /**
     * use reflect
     */
    public static void copyPropertiesByReflect(Object source, Object target) throws RuntimeException, IllegalAccessException, InstantiationException {

        if (source == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (target == null) {
            throw new NullPointerException("Target must not be null");
        }

        Class<?> sourceCls = source.getClass();
        Class<?> targetCls = target.getClass();
        for (Field field : sourceCls.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getType().getClassLoader() != null) {
                Object subTarget = field.get(target);


            }
            String sourceFieldName = field.getName();
            try {
                Object sourceFieldValue = field.get(source);
                //set value
                Field targetField = getField(targetCls, sourceFieldName);

                if (targetField != null) {
                    targetField.getDeclaringClass();
                    targetField.setAccessible(true);
                    targetField.set(target, sourceFieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Field getField(Class clazz, String fieldName) {
        try {

            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
        }
        return null;
    }

    /**
     * use introspector
     */
    public static void copyPropertiesByIntrospector(Object source, Object target) throws RuntimeException {
        if (source == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (target == null) {
            throw new NullPointerException("Target must not be null");
        }
        try {
            BeanInfo targetBeanInfo = Introspector.getBeanInfo(target.getClass());

            PropertyDescriptor [] sourcePropertyDescriptors= targetBeanInfo.getPropertyDescriptors();
            if(sourcePropertyDescriptors.length <= 0){
                throw new RuntimeException("target has no property");
            }
            for (PropertyDescriptor targetPropertyDescriptor : sourcePropertyDescriptors) {
                Class targetFieldType = targetPropertyDescriptor.getPropertyType();
                if( targetFieldType.getClassLoader() == null){
                    Method targetMethod = targetPropertyDescriptor.getWriteMethod();
                    String targetFieldName = targetPropertyDescriptor.getName();

                    System.out.println("targetFieldType.getClassLoader()\t" + targetFieldType.getClassLoader());

                    PropertyDescriptor sourcePropertyDescriptor =BeanUtils.getPropertyDescriptor(source.getClass(),targetFieldName);
                    if(!"class".equals(sourcePropertyDescriptor.getName())){
                        Method sourceMethod = sourcePropertyDescriptor.getReadMethod();
                        Object sourceValue = sourceMethod.invoke(source);
                        targetMethod.invoke(target,sourceValue);
                    }
                }else {
                    //targetPropertyDescriptor
                    BeanInfo beanInfo = Introspector.getBeanInfo(targetPropertyDescriptor.getClass());


                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        source.getClass();

    }
    private static void setTartgetValue(Class source,Class target){
        try {
            if(source == null){
                target = null;
                return;
            }

            BeanInfo sourceBeanInfo = Introspector.getBeanInfo(source);
            BeanInfo targetBeanInfo = Introspector.getBeanInfo(target);

            PropertyDescriptor [] targetPropertyDescriptor = targetBeanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : targetPropertyDescriptor){
                //propertyDescriptor.

            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }
}