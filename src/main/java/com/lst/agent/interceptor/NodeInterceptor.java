package com.lst.agent.interceptor;

import com.lst.agent.context.EventContext;
import com.lst.agent.event.NormalEvent;
import com.lst.agent.node.MethodNode;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by li on 2018/1/4.
 */


public class NodeInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {


        //System.out.println("methodNode:"+method);

        NormalEvent event = EventContext.getEvent();
        MethodNode node = new MethodNode();
        event.addMethodNode(node);

//        TimeRecordBean bean = new TimeRecordBean();
//        bean.setClassesName(method.toString());
//        bean.setSuccess(true);

        //node.setRecordBean(bean);
        node.setStartTime(System.currentTimeMillis());
        node.setMethodName(method.toString());

        Object obj = callable.call();

        MethodNode methodNode = EventContext.getMethodNode();
        if(methodNode!=null && methodNode!=node){
            node.addMethodMode(methodNode);
        }

        node.setEndTime(System.currentTimeMillis());
        EventContext.setMethodNode(node);

        return obj;
    }
}