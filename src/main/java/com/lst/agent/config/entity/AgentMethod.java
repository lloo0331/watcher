package com.lst.agent.config.entity;
/**
 * Created by li on 2018/1/22.
 */

import com.alibaba.fastjson.JSON;
import com.lst.agent.util.AgentHelp;
import net.bytebuddy.agent.builder.AgentBuilder;

public class AgentMethod {

    private String methodName;

    private String matchType;

    private String interceptor;

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public void setInterceptor(String interceptor) {
        this.interceptor = interceptor;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMatchType() {
        return matchType;
    }

    public String getInterceptor() {
        return interceptor;
    }

    public String getMethodName() {
        return methodName;
    }

    public void exec(AgentBuilder builder){
        AgentBuilder.Transformer transformer = AgentHelp.createTransformer(interceptor,"any","any");

//        builder.type(AgentHelp.getMatcher(className,matchType));
//        if(method!=null){
//            method.exec(builder);
//        }
    }

    @Override
    public String toString() {
        return "AgentMethod{" +
                "methodName='" + methodName + '\'' +
                ", matchType='" + matchType + '\'' +
                ", interceptor='" + interceptor + '\'' +
                '}';
    }
}
