package com.whaleson.cloneable.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

public class UtilsBackup2 {

    /**
     * @param source source object
     * @param target target object
     * */
    public static void copyPropertiesByReflect(Object source, Object target) throws RuntimeException, IllegalAccessException, InstantiationException {

        if (source == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (target == null) {
            throw new NullPointerException("Target must not be null");
        }
        Class<?> sourceClz = source.getClass();
        Class<?> targetCls = target.getClass();
        //遍历源对象的所有成员变量
        for (Field field : sourceClz.getDeclaredFields()) {
            field.setAccessible(true);
            //获取源对象成员变量的值
            Object sourceFieldValue = field.get(source);
            //获取源对象成员变量的变量名
            String sourceFieldName = field.getName();
            //根据源对象的成员变量名获取目标对象的字段。
            Field targetField = getField(targetCls, sourceFieldName);
            //只处理获取到的目标对象的字段
            if(targetField != null){
                targetField.setAccessible(true);
                //字段的类型是否为JDK原生类型
                if (field.getType().getClassLoader() != null) {
                    //如果是非JDK原生类型则获取目标对象的字段
                    Object subTarget = targetField.get(target);
                    if(subTarget == null){
                        subTarget = targetField.getType().newInstance();
                    }

                    copyPropertiesByReflect(sourceFieldValue,subTarget);
                    targetField.set(target,subTarget);
                }else{
                    try {
                        if (targetField != null) {
                            if(sourceFieldValue instanceof List){

                                Object  targetFieldObject = targetField.get(target);


                                ParameterizedType pt = (ParameterizedType) targetField.getGenericType();
                                Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0];
                                System.out.println("ha\t" + clazz.getName());
                                System.out.println("996\t" + targetField.getGenericType());
                                if(targetFieldObject == null){
                                    targetFieldObject = Class.forName(targetField.getGenericType().getTypeName()).newInstance();
                                    //targetFieldObject = targetField.getGenericType().getClass().newInstance();
                                }
                                System.out.println("999\t" + targetFieldObject.getClass().getName());



                                targetField.set(target, sourceFieldValue);
                            }else if(sourceFieldValue instanceof Set){

                            }else{
                                targetField.set(target, sourceFieldValue);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
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

}
