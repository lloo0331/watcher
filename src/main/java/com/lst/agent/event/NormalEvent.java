package com.lst.agent.event;

import com.alibaba.fastjson.JSON;
import com.lst.agent.node.MethodNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by li on 2018/1/6.
 */


public class NormalEvent {


    private long traceId;

    private long startTime;

    private long endTime;

    private long costTime;

    private List<MethodNode> methodNodes = new LinkedList<>();

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTraceId() {
        return traceId;
    }

    public List<MethodNode> getMethodNodes() {
        return methodNodes;
    }

    public void setTraceId(long traceId) {
        this.traceId = traceId;
    }

    public void setMethodNodes(List<MethodNode> methodNodes) {
        this.methodNodes = methodNodes;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        this.costTime = endTime-startTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public void addMethodNode(MethodNode methodNode){
        methodNodes.add(methodNode);
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getCostTime() {
        return costTime;
    }
}
