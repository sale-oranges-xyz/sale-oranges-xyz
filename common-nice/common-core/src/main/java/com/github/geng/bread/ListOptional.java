package com.github.geng.bread;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 封装list stream 操作
 * @author geng
 */
public class ListOptional {

    /**
     *  封装list steam的map转换流
     *  <pre></pre>
     * @param list 输入集合
     * @param mapper map处理
     * @param <T> 输入list数据类型
     * @param <U> 返回list的数据类型
     * @return 转换后的ArrayList
     */
    public static <T,U> List<U> createArrayList(List<T> list, Function<? super T, ? extends U> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        //map是转换流
        return list.stream().map(mapper).collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        List<Integer> orignalList = Arrays.asList(1);
//        List<String> targetList = ListOptional.createArrayList(orignalList, String::valueOf);
//        targetList.forEach(System.out::println);
//    }
}
