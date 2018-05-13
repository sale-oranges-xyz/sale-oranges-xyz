package com.github.geng.util;

import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 转换工具类
 * @author geng
 */
@Slf4j
public class ConverterUtils {
    private ConverterUtils() {}

    /**
     * string 转int,无法转换时返回null
     * @param vlaue
     * @return
     */
    public static Integer stringToInt(String vlaue) {
        IntegerStringConverter integerStringConverter = new IntegerStringConverter();
        try {
           return integerStringConverter.fromString(vlaue);
        } catch (NumberFormatException e) {
            log.error("数据解析异常",e);
            return null;
        }
    }

    /**
     * string 转int， 无法转换时使用默认值
     * @param vlaue
     * @param defaultValue
     * @return
     */
    public static int StringToIntDefault(String vlaue,int defaultValue) {
        IntegerStringConverter integerStringConverter = new IntegerStringConverter();
        try {
            Optional<Integer> value = Optional.ofNullable(integerStringConverter.fromString(vlaue));
            return value.orElse(defaultValue);
        } catch (NumberFormatException e) {
            log.error("数据解析异常",e);
            return defaultValue;
        }
    }

}
