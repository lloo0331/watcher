package com.lst.agent.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by li on 2018/1/6.
 */

public class MethodNode {

    private long costTime;

    private String methodName;

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

}
