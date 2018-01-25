package com.lst.agent.context;

import com.lst.agent.entity.NormalEvent;

/**
 * Created by li on 2018/1/6.
 */


public class EventContext {

    private static ThreadLocal<NormalEvent> CONTEXT = new ThreadLocal<NormalEvent>();

    private static ThreadLocal<Long> START_TIME = new ThreadLocal<Long>();

    public static NormalEvent createEvent(){
        NormalEvent event = new NormalEvent();
        long id = 1L;

        event.setTraceId(id);
        CONTEXT.set(event);
        return event;
    }

    public static NormalEvent getEvent(){
        NormalEvent event = CONTEXT.get();
        return event;
    }

    public static void clear(){
        CONTEXT.set(null);
    }

    public static NormalEvent getOrCreateEvent(){
        NormalEvent event = getEvent();
        if(event==null){
            event = createEvent();
        }
        return event;
    }

    public static long getOrCreStartTime(){
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
