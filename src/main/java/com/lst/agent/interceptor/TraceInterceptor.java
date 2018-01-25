package com.lst.agent.interceptor;


import com.lst.agent.context.TraceContext;
import com.lst.agent.entity.TraceEntity;
import com.sun.deploy.trace.Trace;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 节点
 * Created by li on 2018/1/4.
 */
public class TraceInterceptor extends Interceptor{
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable, @This Object thisObj, @AllArguments Object[] arguments,@Origin Class classes) throws Exception {

        Object obj = null;
        TraceEntity entity = null;
        try{
            entity = TraceContext.createTraceEntity(classes.getName(),method.getName());
            obj = callable.call();
            return obj;
        }finally {
            entity.setEndTime(System.currentTimeMillis());
            if(entity.getTraceId()== TraceContext.getSEED().get()){
                //System.out.println("------->树尾:"+entity.getTraceId());
            }else{
                while (true){
                    TraceEntity child = TraceContext.getSTACK().pop();
                    if(child==entity){
                        TraceContext.getSTACK().push(child);
                        break;
                    }else{
                        entity.addTrace(child);
                    }
                }
            }
            if(entity.getTraceId()==1){
                System.out.println(entity);
                TraceContext.clearStack();
            }
        }

    }
}