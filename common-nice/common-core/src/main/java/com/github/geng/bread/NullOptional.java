package com.github.geng.bread;

import java.util.function.Function;

/**
 * 对象验证，null返回null，使用使用optional类代替
 * @author geng
 */
public class NullOptional {

    /**
     * 封装逻辑，对象为null时返回null
     * @param t
     * @param mapper
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T,U> U  validate(T t, Function<? super T, ? extends U> mapper) {
        if (null == t) {
            return null;
        }
        return mapper.apply(t);
    }
}
