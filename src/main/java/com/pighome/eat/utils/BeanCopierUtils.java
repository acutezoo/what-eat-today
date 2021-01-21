package com.pighome.eat.utils;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

/**
 * 代理的方式实现对象拷贝的工具类【使用cglib的BeanCopier复制对象的工具类】 注意： BeanCopier只拷贝名称和类型都相同的属性; 当目标类的setter数目比getter少时，创建BeanCopier会失败而导致拷贝不成功 引用类型不会拷贝，直接引用，所以操作源对象的引用类型属性会导致copy的对象的引用类型属性一起变化
 */
public class BeanCopierUtils {

    private static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();

    /**
     * 将源对象的属性值拷贝到目标对象中
     *
     * @param source 源对象
     * @param targetClass 目标类
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        String beanKey = generateKey(source.getClass(), targetClass);
        BeanCopier copier;
        if (!beanCopierMap.containsKey(beanKey)) {
            // 第三个参数：是否开启Convert, 默认BeanCopier只会做同名，同类型属性的copier,否则就会报错
            copier = BeanCopier.create(source.getClass(), targetClass, false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }

        T targetObject;
        try {
            targetObject = targetClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        copier.copy(source, targetObject, null);

        return targetObject;
    }

    /**
     * 将源对象的属性值拷贝到目标对象中
     *
     * @param source 源对象
     * @param target 目标类
     */
    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    /**
     * 将源对象列表拷贝到目标对象列表中
     *
     * @param source 源对象列表
     * @param target 目标对象列表
     * @param target 目标对象类
     */
    public static void copyListProperties(List<?> source, List<Object> target, Class<?> targetClass) throws Exception {
        if (CollectionUtils.isEmpty(source) || target == null) {
            return;
        }

        for (Object obj : source) {
            Object targetObject = targetClass.newInstance();
            copyProperties(obj, targetObject);

            target.add(targetObject);
        }
    }

    /**
     * 将源对象列表拷贝到目标对象列表中
     *
     * @param source 源对象列表
     * @param targetClass 目标对象類型
     */
    public static <T> List<T> copyListProperties(List<?> source, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(source)) {
            return Lists.newArrayList();
        }

        List<T> target = new ArrayList<>();
        try {
            T targetObject;
            for (Object obj : source) {
                targetObject = targetClass.newInstance();
                copyProperties(obj, targetObject);
                target.add(targetObject);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
