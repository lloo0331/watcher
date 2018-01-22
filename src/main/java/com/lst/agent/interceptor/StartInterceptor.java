package com.lst.agent.interceptor;

import com.lst.agent.context.EventContext;
import com.lst.agent.entity.MethodNode;
import com.lst.agent.entity.NormalEvent;
import com.lst.agent.entity.TimeRecordBean;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by li on 2018/1/4.
 * 开始节点
 */


public class StartInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {
        NormalEvent event = EventContext.getEvent();
        MethodNode node = new MethodNode();
        //event.addMethodNode(node);

        TimeRecordBean bean = new TimeRecordBean();
        bean.setClassesName(method.toString());

        //node.setRecordBean(bean);
        long time = System.currentTimeMillis();

        Object obj = callable.call();

        node.setCostTime(System.currentTimeMillis()-time);

        return obj;
    }
}