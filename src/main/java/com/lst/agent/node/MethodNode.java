package com.lst.agent.node;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by li on 2018/1/6.
 */

public class MethodNode {

    //RecordBean recordBean;

    List<MethodNode> methodNodeList = new LinkedList<>();

    private long startTime;

    private long endTime;

    private long costTime;

    private String methodName;

//    public void setRecordBean(RecordBean recordBean) {
//        this.recordBean = recordBean;
//    }
//
//    public RecordBean getRecordBean() {
//        return recordBean;
//    }

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

    public void setMethodNodeList(List<MethodNode> methodNodeList) {
        this.methodNodeList = methodNodeList;
    }

    public List<MethodNode> getMethodNodeList() {
        return methodNodeList;
    }

    public void addMethodMode(MethodNode node){
        methodNodeList.add(node);
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
