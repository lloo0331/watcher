package com.lst.agent.interceptor;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;


/**
 * Created by li on 2018/1/4.
 */


public class JsonInterceptor {
    @RuntimeType
    public static Object intercept(@This Object thisObj) throws Exception {
        return JSON.toJSONString(thisObj);
    }
}