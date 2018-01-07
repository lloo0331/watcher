package com.lst.agent.entity;

import com.lst.agent.node.MethodNode;

/**
 * Created by li on 2018/1/7.
 */
@Deprecated
public class MethodObject {

    private Object obj;

    private long traceId;

    private MethodNode methodNode;

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
