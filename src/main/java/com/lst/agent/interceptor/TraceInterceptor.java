package com.lst.agent.interceptor;


import com.lst.agent.context.TraceContext;
import com.lst.agent.entity.TraceEntity;
import com.lst.agent.util.AgentHelp;
import net.bytebuddy.implementation.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 节点
 * Created by li on 2018/1/4.
 */
public class TraceInterceptor extends Interceptor{

    private static Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable, @This Object thisObj, @AllArguments Object[] arguments,@Origin Class classes) throws Exception {

        Object obj = null;
        TraceEntity entity = TraceContext.createTraceEntity(classes.getName(),method.getName());
        try{
            obj = callable.call();
            return obj;
        }finally {
            entity.setEndTime(System.currentTimeMillis());
            if(entity.getSpanId()== TraceContext.getSEED().get()){
                //System.out.println("------->树尾:"+entity.getSpanId());
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
            if(entity.getSpanId()==1){
                logger.info(entity.toString());
                //System.out.println(entity);
                TraceContext.clearStack();
            }
        }

    }
}