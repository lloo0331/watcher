package com.lst.agent.interceptor;


import com.lst.agent.context.EventContext;
import com.lst.agent.entity.MethodNode;
import com.lst.agent.entity.NormalEvent;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 节点
 * Created by li on 2018/1/4.
 */
public class NodeInterceptor extends Interceptor{
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {
        NormalEvent event = EventContext.getEvent();
        MethodNode node = new MethodNode();

        node.setMethodName(method.toString());

        event.addMethodNode(node);

        long startTime = System.currentTimeMillis();

        try{
            Object obj = callable.call();
            return obj;
        }finally {
            long endTime = System.currentTimeMillis();

            node.setCostTime(endTime-startTime);
        }

    }
}