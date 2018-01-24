package com.lst.agent.context;
/**
 * Created by li on 2018/1/24.
 */

import com.lst.agent.entity.TraceEntity;

import java.util.HashMap;
import java.util.Map;

public class TraceContext {

    private static ThreadLocal<Map<Integer,TraceEntity>> CONTEXT = new ThreadLocal<>();//容器

    private static ThreadLocal<Integer> SEED = new ThreadLocal<>();//种子

    private static ThreadLocal<Integer> PARENT_ID = new ThreadLocal<>();//父亲节点的ID

    public static ThreadLocal<Integer> getParentId() {
        return PARENT_ID;
    }

    public static ThreadLocal<Integer> getSEED() {
        return SEED;
    }

    public static ThreadLocal<Map<Integer, TraceEntity>> getCONTEXT() {
        return CONTEXT;
    }

    public static void decParentId(){
        PARENT_ID.set(PARENT_ID.get()-1);
    }

    static {
        CONTEXT.set(new HashMap<>());
        SEED.set(0);
        PARENT_ID.set(0);
    }

    public static TraceEntity createTraceEntity(String className, String methodName){
        TraceEntity entity = new TraceEntity();
        entity.setClassName(className);
        entity.setMethodName(methodName);

        Integer parentId = PARENT_ID.get();

        TraceEntity parent = CONTEXT.get().get(parentId);

        if(parent!=null){
            entity.setParent(parent);//设置父节点
            parent.addTrace(entity);//增加子节点
        }

        Integer seed = SEED.get();
        seed++;
        SEED.set(seed);

        PARENT_ID.set(seed);//设置当前父节点

        entity.setTraceId(seed);//设置id
        entity.setStartTime(System.currentTimeMillis());

        Map<Integer,TraceEntity> entityMap = CONTEXT.get();
        entityMap.put(seed,entity);

        return entity;
    }

//    public static NormalEvent getEvent(){
//        NormalEvent event = CONTEXT.get();
//        return event;
//    }
//
//    public static void clear(){
//        CONTEXT.set(null);
//    }
//
//    public static NormalEvent getOrCreateEvent(){
//        NormalEvent event = getEvent();
//        if(event==null){
//            event = createEvent();
//        }
//        return event;
//    }
//
}
