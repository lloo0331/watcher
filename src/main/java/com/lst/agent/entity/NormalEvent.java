package com.lst.agent.entity;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by li on 2018/1/6.
 */


public class NormalEvent {

    private long traceId;

    private long costTime;

    private List<MethodNode> methodNodes = new LinkedList<>();

    private MethodNode methodNode = null;

    public long getTraceId() {
        return traceId;
    }

    public void setTraceId(long traceId) {
        this.traceId = traceId;
    }

//    @Override
//    public String toString() {
//        return JSON.toJSONString(this);
//    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void addMethodNode(MethodNode methodNode){
        methodNodes.add(methodNode);
    }

    public List<MethodNode> getMethodNodes() {
        return methodNodes;
    }

    public void setMethodNode(List<MethodNode> methodNodes) {
        this.methodNodes = methodNodes;
    }

    public void setMethodNode(MethodNode methodNode) {
        this.methodNode = methodNode;
    }

    public MethodNode getMethodNode() {
        return methodNode;
    }
}
