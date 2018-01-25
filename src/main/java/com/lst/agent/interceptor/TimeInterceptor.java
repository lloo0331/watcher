package com.lst.agent.interceptor;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 时间打印,为指定的方法添加执行耗时打印
 * Created by li on 2018/1/4.
 */
public class TimeInterceptor extends Interceptor{

    public static long printTime = 20;//耗时大于等于该时间进行输出,单位毫秒ms
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {

        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            return callable.call();
        } finally {
            long ms = System.currentTimeMillis()-start;
            if(ms>=printTime){
                System.out.println(method + ": took " + ms + "ms");
            }

        }
    }
}