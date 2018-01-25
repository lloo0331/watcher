package com.lst.agent.entity;
/**
 * Created by li on 2018/1/24.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TraceEntity {

    private int traceId;//id

    //private int parentTraceId;//父亲节点的id
    @JSONField(serialize = false)
    private TraceEntity parent;//父亲节点

    private List<TraceEntity> list = new ArrayList<>();//孩子节点

    private String className;//类名

    private String methodName;//方法名

    @JSONField(serialize = false)
    private long startTime;//开始时间

    @JSONField(serialize = false)
    private long endTime;//结束时间

    private long costTime;//消费时间

    public void setTraceId(int traceId) {
        this.traceId = traceId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParent(TraceEntity parent) {
        this.parent = parent;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setList(Stack<TraceEntity> list) {
        this.list = list;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getTraceId() {
        return traceId;
    }

    public TraceEntity getParent() {
        return parent;
    }

    public List<TraceEntity> getList() {
        return list;
    }

    public String getClassName() {
        return className;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void addTrace(TraceEntity entity){
        list.add(0,entity);
        //list.add(entity,0);
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getCostTime() {
        return costTime;
    }

    //    public void setParentTraceId(int parentTraceId) {
//        this.parentTraceId = parentTraceId;
//    }
//
//    public int getParentTraceId() {
//        return parentTraceId;
//    }

    @Override
    public String toString() {
        return "TraceEntity{" +
                "traceId=" + traceId +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", costTime=" + (endTime-startTime)+
                '}';
    }

    public void print(){
//        System.out.println(toString());
//        for(TraceEntity e:list){
//            e.print();
//        }
    }
}
