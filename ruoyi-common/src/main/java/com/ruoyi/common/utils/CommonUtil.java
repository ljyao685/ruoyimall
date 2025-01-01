package com.ruoyi.common.utils;

import java.lang.reflect.Field;

/**
 * @Author: lb
 * @Date: 2022/1/25 14:45
 * @desc：公共方法
 */
public class CommonUtil {
    /**
     * 单个对象的某个键的值
     *
     * @param obj 对象
     * @param key 键
     * @return Object 键在对象中所对应得值 没有查到时返回空字符串
     */
    public static Object getValueByKey(Object obj, String key) {
        // 得到类对象
        Class clazz = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {//向上循环 遍历父类
            Field[] fs = clazz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                try {

                    if (f.getName().endsWith(key)) {
                        return f.get(obj);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
