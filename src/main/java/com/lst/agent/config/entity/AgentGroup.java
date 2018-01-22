package com.lst.agent.config.entity;
/**
 * Created by li on 2018/1/22.
 */


public class AgentGroup {

    AgentClass classes;

    AgentMethod method;

    public void setClasses(AgentClass classes) {
        this.classes = classes;
    }

    public void setMethod(AgentMethod method) {
        this.method = method;
    }

    public AgentClass getClasses() {
        return classes;
    }

    public AgentMethod getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "AgentGroup{" +
                "classes=" + classes +
                ", method=" + method +
                '}';
    }

}
