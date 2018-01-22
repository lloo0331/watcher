package com.lst.agent.interceptor;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;


/**
 * 将指定的方法修改成返回json字符串,可以用于修改toString方法
 * Created by li on 2018/1/4.
 */


public class JsonInterceptor extends Interceptor{
    @RuntimeType
    public static Object intercept(@This Object thisObj) throws Exception {
        return JSON.toJSONString(thisObj);
    }
}