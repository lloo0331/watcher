package com.lst.agent.config.entity;
/**
 * Created by li on 2018/1/22.
 * 代理方法
 */
import net.bytebuddy.agent.builder.AgentBuilder;

public class AgentMethod {
    /**代理的方法名*/
    private String methodName;
    /**匹配代理方法的方式*/
    private String matchType;
    /**拦截器名称*/
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
