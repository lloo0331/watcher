package com.lst.agent.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by li on 2018/1/6.
 */

public class MethodNode1 {

    //RecordBean recordBean;

    @JSONField(serialize=false)
    List<MethodNode1> methodNodeList = new LinkedList<>();

    @JSONField(serialize=false)
    private long startTime;

    @JSONField(serialize=false)
    private long endTime;

    private long costTime;

    private String methodName;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        this.costTime = endTime-startTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setMethodNodeList(List<MethodNode1> methodNodeList) {
        this.methodNodeList = methodNodeList;
    }

    public List<MethodNode1> getMethodNodeList() {
        return methodNodeList;
    }

    public void addMethodMode(MethodNode1 node){
        methodNodeList.add(node);
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

//    @Override
//    public String toString() {
//        return JSON.toJSONString(this);
//    }

}
