package com.pighome.eat.utils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8 lambda 工具类
 */
public class StreamUtil {

    /**
     * 按给定的函数，处理List
     *
     * @param data
     * @param mapFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> map(List<T> data, Function<T, R> mapFunc) {
        if (CollUtil.isEmpty(data)) {
            return Lists.newArrayList();
        }
        return data.stream().map(mapFunc).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 按给定的函数，处理List并去重
     *
     * @param data
     * @param mapFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> mapDistinct(List<T> data, Function<T, R> mapFunc) {
        if (null == data) {
            return Lists.newArrayList();
        }
        return data.stream().filter(Objects::nonNull).map(mapFunc).filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
    }

    /**
     * 按给定的函数，过滤list
     *
     * @param data
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> data, Predicate<T> predicate) {
        return data.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 按给定的函数，找到任意一个
     *
     * @param data
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> Optional<T> findAny(List<T> data, Predicate<T> predicate) {
        return data.stream().filter(predicate).findAny();
    }

    /**
     * 将map中的value汇总成一个list集合
     *
     * @param data
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> mapValueToList(Map<T, R> data) {
        return new ArrayList<>(data.values());
    }

    /**
     * list to Map ,以T的指定的属性为 key，T自身为value的map
     *
     * @param list
     * @param keyMapper
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, T> listToMap(List<T> list,
                                             Function<? super T, ? extends K> keyMapper) {
        return listToMap(list, keyMapper, Function.identity());
    }

    /**
     * list 先过滤再转成map
     *
     * @param list
     * @param predicate
     * @param keyMapper
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, T> filterAndMap(List<T> list, Predicate<T> predicate,
                                                Function<? super T, ? extends K> keyMapper) {
        return listToMap(filter(list, predicate), keyMapper, Function.identity());
    }

    /**
     * list to Map ,以T的指定的属性为 key，指定规则为value的map
     *
     * @param list
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <K>
     * @param <U>
     * @return
     */
    public static <T, K, U> Map<K, U> listToMap(List<T> list,
                                                Function<? super T, ? extends K> keyMapper,
                                                Function<? super T, ? extends U> valueMapper) {
        if (CollUtil.isEmpty(list)) {
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1));
    }

    /**
     * list to LinkedHashMap ,以T的指定的属性为 key，指定规则为value的map
     *
     * @param list
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <K>
     * @param <U>
     * @return
     */
    public static <T, K, U> LinkedHashMap<K, U> listToLinkedHashMap(List<T> list,
                                                                    Function<? super T, ? extends K> keyMapper,
                                                                    Function<? super T, ? extends U> valueMapper) {
        return list.stream().collect(
                Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1, LinkedHashMap::new));
    }

    /**
     * 获取list第一个元素
     *
     * @param list
     * @return
     */
    public static <K, T> Optional<K> getOptValOfFirst(List<T> list,
                                                      Function<? super T, ? extends K> keyMapper) {
        return Optional.of(list)
                .filter(CollUtil::isNotEmpty)
                .map(p -> p.get(0))
                .map(keyMapper);
    }

    /**
     * 获取list第一个元素
     *
     * @param list
     * @return
     */
    public static <T> Optional<T> getOptOfFirst(List<T> list) {
        return Optional.of(list).filter(CollUtil::isNotEmpty).map(p -> p.get(0));
    }

    /**
     * list转map list
     *
     * @param list
     * @param classifier
     * @param <T>
     * @return
     */
    public static <T, K> Map<K, List<T>> ListToMapList(List<T> list,
                                                       Function<? super T, ? extends K> classifier) {
        return list.stream().collect(Collectors.groupingBy(classifier));
    }


    /**
     * list转map list map有序
     *
     * @param list
     * @param classifier
     * @param <T>
     * @return
     */
    public static <T, K> Map<K, List<T>> listToOrderedMapList(List<T> list,
                                                              Function<? super T, ? extends K> classifier) {
        return list.stream().collect(
                Collectors.groupingBy(classifier, LinkedHashMap::new, Collectors.toList()));
    }

    /**
     * list转map list map有序
     *
     * @param list
     * @param classifier
     * @param <T>
     * @return
     */
    public static <T, K, D> Map<K, List<D>> listToOrderedMapList(List<T> list,
                                                                 Function<? super T, ? extends K> classifier,
                                                                 Function<? super T, ? extends D> mapper) {
        return list.stream().collect(Collectors.groupingBy(classifier, LinkedHashMap::new,
                Collectors.mapping(mapper, Collectors.toList())));
    }

    /**
     * list转map list
     *
     * @param list
     * @param classifier
     * @param mapper
     * @param <T>
     * @param <K>
     * @param <D>
     * @return
     */
    public static <T, K, D> Map<K, List<D>> ListToMapList(List<T> list,
                                                          Function<? super T, ? extends K> classifier,
                                                          Function<? super T, ? extends D> mapper) {
        return list.stream().collect(
                Collectors.groupingBy(classifier, Collectors.mapping(mapper, Collectors.toList())));
    }

    /**
     * List toSet
     *
     * @param data
     * @param mapFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Set<R> listToSet(List<T> data, Function<T, R> mapFunc) {
        return data.stream().filter(Objects::nonNull).map(mapFunc).filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * 根据属性进行去重
     *
     * @param list         数据list
     * @param keyExtractor keyExtractor function
     * @param <T>          数据源类型
     * @param <R>          属性类型
     * @return 去重结果
     */
    public static <T, R> List<T> distinctByKey(List<T> list, Function<T, R> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        Predicate<T> predicate = t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
}
