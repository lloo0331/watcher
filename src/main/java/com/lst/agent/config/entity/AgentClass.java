package com.lst.agent.config.entity;

import com.lst.agent.util.AgentHelp;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * Created by li on 2018/1/22.
 */

public class AgentClass {

    private String className;

    private String matchType;

    private AgentMethod method;

    public void setMethod(AgentMethod method) {
        this.method = method;
    }

    public AgentMethod getMethod() {
        return method;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getClassName() {
        return className;
    }

    public String getMatchType() {
        return matchType;
    }

    @Override
    public String toString() {
        return "AgentClass{" +
                "className='" + className + '\'' +
                ", matchType='" + matchType + '\'' +
                ", method=" + method +
                '}';
    }

    public void exec(AgentBuilder builder){
        builder.type(AgentHelp.getMatcher(className,matchType));
        if(method!=null){
            method.exec(builder);
        }
    }


}
