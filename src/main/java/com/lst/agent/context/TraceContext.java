package com.lst.agent.context;
/**
 * Created by li on 2018/1/24.
 */

import com.lst.agent.entity.TraceEntity;

import java.util.Stack;

public class TraceContext {

    private static ThreadLocal<Integer> SEED = new ThreadLocal<>();//种子

    private static ThreadLocal<Stack<TraceEntity>> STACK = new ThreadLocal<>();//栈

    public static Stack<TraceEntity> getSTACK() {
        return STACK.get();
    }

    public static void clearStack(){
        STACK.get().clear();
        SEED.set(0);
    }

    public static ThreadLocal<Integer> getSEED() {
        return SEED;
    }

    static {
        //CONTEXT.set(new HashMap<>());
        SEED.set(0);
       // PARENT_ID.set(0);
        STACK.set(new Stack<>());
    }

    public static TraceEntity createTraceEntity(String className, String methodName){
        TraceEntity entity = new TraceEntity();
        entity.setClassName(className);
        entity.setMethodName(methodName);

        Integer seed = SEED.get();
        if(seed==null){
            seed = 0;
        }
        seed++;
        SEED.set(seed);

        entity.setSpanId(seed);//设置id
        entity.setStartTime(System.currentTimeMillis());

        Stack<TraceEntity> stack = STACK.get();
        if(stack==null){
            stack = new Stack<>();
            STACK.set(stack);
        }
        stack.push(entity);

        return entity;
    }


}
