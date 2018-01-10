package com.lst.agent.interceptor;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 结束节点
 * Created by li on 2018/1/4.
 */


public class EndInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {

//        NormalEvent event = EventContext.getEvent();
//        MethodNode1 node = new MethodNode1();
//        event.setMethodNode(node);
//
//        TimeRecordBean bean = new TimeRecordBean();
//        bean.setClassesName(method.toString());
//
//        //node.setRecordBean(bean);
//        node.setStartTime(System.currentTimeMillis());
//
//        Object obj = callable.call();
//
//        node.setEndTime(System.currentTimeMillis());
//
//        event.setEndTime(System.currentTimeMillis());
//
//        System.out.println(event);

        return callable.call();
    }
}