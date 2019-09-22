package com.whaleson.cloneable.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Stream;

public class UtilsList {

    /**
     * 通过反射将source复制给target,根据成员变量的变量名进行操作，其中，成员变量类型支持自定义类型、List类型、Set类型
     * @param source source object
     * @param target target object
     * @throws RuntimeException
     * @throws IllegalAccessException
     * @throws InstantiationException
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

                                List<Object> subTargetList = (List<Object>)targetField.get(target);
                                if(subTargetList == null){
                                    subTargetList = (List<Object>)targetField.getType().newInstance();
                                }
                                ListIterator listIterator = ((List) sourceFieldValue).listIterator();
                                while (listIterator.hasNext()){
                                    Object listSourceElement= listIterator.next();

                                    ParameterizedType pt = (ParameterizedType) targetField.getGenericType();
                                    Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0];

                                    Object listTargetElement = clazz.newInstance();
                                    copyPropertiesByReflect(listSourceElement,listTargetElement);
                                    subTargetList.add(listTargetElement);
                                }
                                targetField.set(target, subTargetList);
                            }

                            if(sourceFieldValue instanceof Set){
                                System.out.println(sourceFieldValue);

                                Set<Object> subTargetSet = (Set<Object>)targetField.get(target);
                                if(subTargetSet == null){
                                    System.out.println("666\t"+targetField.getType().getName());
                                    subTargetSet = (Set<Object>)targetField.getType().newInstance();
                                }
                                Iterator<Object> objectIterator = ((Set) sourceFieldValue).stream().iterator();
                                while (objectIterator.hasNext()){
                                    Object objectIteratorItem = objectIterator.next();

                                    ParameterizedType pt = (ParameterizedType) targetField.getGenericType();
                                    Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0];
                                    Object setObject = clazz.newInstance();
                                    copyPropertiesByReflect(objectIteratorItem,setObject);
                                    subTargetSet.add(setObject);
                                }
                                targetField.set(target, subTargetSet);

                            }else{
                                targetField.set(target, sourceFieldValue);
                            }
                        }
                    } catch (IllegalAccessException e) {
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
