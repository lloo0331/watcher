package com.lst.agent.context;

import com.lst.agent.entity.NormalEvent;
import com.lst.agent.entity.MethodNode1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by li on 2018/1/6.
 */


public class EventContext {


    private static ThreadLocal<NormalEvent> CONTEXT = new ThreadLocal<NormalEvent>();

    private static ThreadLocal<MethodNode1> METHOD_NODE = new ThreadLocal<MethodNode1>();


    private static ThreadLocal<Map<Integer,MethodNode1>> METHOD_NODE_MAP = new ThreadLocal<Map<Integer,MethodNode1>>();

    private static ThreadLocal<Long> START_TIME = new ThreadLocal<Long>();

    static {

        Map<Integer,MethodNode1> map = new ConcurrentHashMap<>();
        METHOD_NODE_MAP.set(map);

    }

    public static NormalEvent createEvent(){
        NormalEvent event = new NormalEvent();
        //System.out.println("event is Null");
        long id = 1L;

        event.setTraceId(id);
        //event.setStartTime(System.currentTimeMillis());
        CONTEXT.set(event);
        return event;
    }

    public static NormalEvent getEvent(){
        NormalEvent event = CONTEXT.get();
        return event;
    }

    public static NormalEvent getOrCreateEvent(){
        NormalEvent event = getEvent();
        if(event==null){
            event = createEvent();
        }
        return event;
    }

    public static void setMethodNode(MethodNode1 methodNode){
        if(methodNode!=null){
            METHOD_NODE.set(methodNode);
        }
    }

//    public static void setMethodNode(MethodNode1 methodNode){
//        if(methodNode!=null){
//            Map<Integer,MethodNode1> map = METHOD_NODE_MAP.get();
//            map.put(methodNode.hashCode(),methodNode);
//        }
//    }

    public static MethodNode1 getMethodNode(int hashCode){
        return METHOD_NODE_MAP.get().get(hashCode);
    }

    public static MethodNode1 getMethodNode(){return METHOD_NODE.get();}

    public static long getStartTime(){
        Long time = START_TIME.get();
        if(time==null){
            time = System.currentTimeMillis();
        }
        return time;
    }

    public static void setStartTime(long time){
        START_TIME.set(time);
    }

}
