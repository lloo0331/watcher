package com.lst.agent.record;

import com.alibaba.fastjson.JSON;

/**
 * Created by li on 2018/1/6.
 */

public class TimeRecordBean extends RecordBean{

    private String classesName;

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getClassesName() {
        return classesName;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
