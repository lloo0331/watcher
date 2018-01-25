package com.lst.agent.interceptor;


import com.lst.agent.util.Advice;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 节点
 * Created by li on 2018/1/4.
 */
public class HouseMDInterceptor extends Interceptor{
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable, @This Object thisObj, @AllArguments Object[] arguments,@Origin Class classes) throws Exception {

        Object obj = null;
        try{
            Advice.onMethodBegin(classes.getName(),method.getName(),method.getName(),thisObj,arguments);
            obj = callable.call();
            return obj;
        }finally {
            Advice.onMethodEnd(obj);
        }

    }
}