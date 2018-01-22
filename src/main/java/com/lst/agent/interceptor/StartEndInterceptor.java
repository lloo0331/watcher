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
 * Created by li on 2018/1/4.
 * 开始和结束在同一个节点
 *
 */
public class StartEndInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {

        NormalEvent event = EventContext.getEvent();
        boolean flag = false;
        if(event==null){
            event = EventContext.createEvent();
            flag = true;
        }

        MethodNode node = new MethodNode();
        node.setMethodName(method.toString());
        event.addMethodNode(node);

        long startTime = System.currentTimeMillis();
        Object obj = callable.call();
        long time = System.currentTimeMillis();

        long ms = time-startTime;
        node.setCostTime(ms);
        event.setCostTime(ms);

        if(flag){//避免递归
            //System.out.println("------------->"+ms);
            System.out.println(event);
            EventContext.clear();//清除节点
        }

        return obj;
    }
}