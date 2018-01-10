package com.lst.agent.interceptor;

import com.lst.agent.context.EventContext;
import com.lst.agent.entity.NormalEvent;
import com.lst.agent.entity.MethodNode1;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 节点
 * Created by li on 2018/1/4.
 */


public class NodeInterceptor1 {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {




        //System.out.println("methodNode:"+method);

//        NormalEvent event = EventContext.getEvent();
//        MethodNode1 node = new MethodNode1();//初始节点
//
//        node.setStartTime(System.currentTimeMillis());
//        node.setMethodName(method.toString());
//
//        if(event.getMethodNode()==null){
//            event.setMethodNode(node);
//        }
//
//        Object obj = callable.call();//执行方法
//
//        MethodNode1 methodNode = EventContext.getMethodNode(node.hashCode());
//        if(methodNode!=null){//不能添加自己
//            node.addMethodMode(methodNode);
//        }
//
//        node.setEndTime(System.currentTimeMillis());
//        EventContext.setMethodNode(node);



        //System.out.println("methodNode:"+method);

        NormalEvent event = EventContext.getEvent();
        MethodNode1 node = new MethodNode1();
        //event.addMethodNode(node);

//        TimeRecordBean bean = new TimeRecordBean();
//        bean.setClassesName(method.toString());
//        bean.setSuccess(true);

        //node.setRecordBean(bean);
        node.setStartTime(System.currentTimeMillis());
        node.setMethodName(method.toString());

        Object obj = callable.call();

        MethodNode1 methodNode = EventContext.getMethodNode();
        if(methodNode!=null && methodNode!=node){
            node.addMethodMode(methodNode);
        }

        node.setEndTime(System.currentTimeMillis());
        EventContext.setMethodNode(node);

        return obj;
    }
}