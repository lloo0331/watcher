package com.lst.agent.config.entity;

import com.lst.agent.util.AgentHelp;
import com.lst.agent.util.ClassPathScanner;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.Set;

/**
 * Created by li on 2018/1/22.
 */

public class AgentClass extends AgentElement{

    private String className;

    private String matchType;

    private AgentMethod method;

    private boolean scan;

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

    public void setScan(boolean scan) {
        this.scan = scan;
    }

    public boolean isScan() {
        return scan;
    }

    @Override
    public String toString() {
        return "AgentClass{" +
                "className='" + className + '\'' +
                ", matchType='" + matchType + '\'' +
                ", method=" + method +
                '}';
    }

    public AgentBuilder exec(AgentBuilder builder){
        if(method!=null){
            method.exec(builder);
            AgentBuilder.Transformer transformer = AgentHelp.createTransformer(method.getInterceptor(),method.getMethodName(),method.getMatchType());
            builder = builder.type(AgentHelp.getMatcher(className,matchType)).transform(transformer);
        }else{
            builder = builder.type(AgentHelp.getMatcher(className,matchType)).transform(null);
            System.out.println("method为空");
        }
        return builder;
    }


}
