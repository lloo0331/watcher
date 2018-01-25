package com.lst.agent.context;
/**
 * Created by li on 2018/1/24.
 */

import com.lst.agent.entity.TraceEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TraceContext {

    //private static ThreadLocal<Map<Integer,TraceEntity>> CONTEXT = new ThreadLocal<>();//容器

    private static ThreadLocal<Integer> SEED = new ThreadLocal<>();//种子

    //private static ThreadLocal<Integer> PARENT_ID = new ThreadLocal<>();//父亲节点的ID

    private static ThreadLocal<Stack<TraceEntity>> STACK = new ThreadLocal<>();//栈

//    public static ThreadLocal<Integer> getParentId() {
//        return PARENT_ID;
//    }

    public static Stack<TraceEntity> getSTACK() {
        return STACK.get();
    }

    public static void clearStack(){
        STACK.get().clear();
    }

    public static ThreadLocal<Integer> getSEED() {
        return SEED;
    }

//    public static ThreadLocal<Map<Integer, TraceEntity>> getCONTEXT() {
//        return CONTEXT;
//    }

//    public static void decParentId(){
//        int id = PARENT_ID.get();
//        //if(id>1){
//            id = id-1;
//            PARENT_ID.set(id);
//        //}
//    }

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

//        Integer parentId = PARENT_ID.get();
//
//        TraceEntity parent = CONTEXT.get().get(parentId);

//        int parentTraceId = 0;
//
//        if(parent!=null){
//            entity.setParent(parent);//设置父节点
//            parent.addTrace(entity);//增加子节点
//            parentTraceId = parent.getTraceId();
//        }

        Integer seed = SEED.get();
        seed++;
        SEED.set(seed);

//        PARENT_ID.set(parentTraceId+1);//设置当前父节点

        entity.setTraceId(seed);//设置id
        entity.setStartTime(System.currentTimeMillis());

//        Map<Integer,TraceEntity> entityMap = CONTEXT.get();
//        entityMap.put(seed,entity);

        STACK.get().push(entity);

        return entity;
    }


}
