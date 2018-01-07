package com.lst.agent.context;

import com.lst.agent.event.NormalEvent;
import com.lst.agent.node.MethodNode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by li on 2018/1/6.
 */


public class EventContext {


    private static ThreadLocal<NormalEvent> CONTEXT = new ThreadLocal<NormalEvent>();

    private static ThreadLocal<MethodNode> METHOD_NODE = new ThreadLocal<MethodNode>();

    public static NormalEvent getEvent(){

        NormalEvent event = CONTEXT.get();
        if(event==null){
            //System.out.println("event is Null");
            long id = 1L;
            event = new NormalEvent();
            event.setTraceId(id);
            event.setStartTime(System.currentTimeMillis());
            CONTEXT.set(event);
        }

        return event;
    }

    public static void setMethodNode(MethodNode methodNode){
        if(methodNode!=null){
            METHOD_NODE.set(methodNode);
        }
    }

    public static MethodNode getMethodNode(){
        return METHOD_NODE.get();
    }

}
