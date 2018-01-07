package com.lst.agent.entity;
/**
 * Created by li on 2018/1/7.
 */
@Deprecated
public class AgentObject {

    private Object obj;

    private long traceId;

    public void setTraceId(long traceId) {
        this.traceId = traceId;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public long getTraceId() {
        return traceId;
    }

    public Object getObj() {
        return obj;
    }
}
