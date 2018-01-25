package com.lst.agent.match;

import com.lst.agent.util.ClassPathScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lst on 2018/1/22.
 */
public class MatchCenter {

    public static Map<String,Class> interceptorMap = new HashMap<>();

    public static Map<String,Class> agentEntityMap = new HashMap<>();

    static {
        interceptorMap = transForm("com.lst.agent.interceptor");

        agentEntityMap =transForm("com.lst.agent.config.entity");

    }

    private static Map<String,Class> transForm(String path) {
        Set<Class> set1 =  ClassPathScanner.scan(path,true,false,false,null);
        return set1.stream().collect(Collectors.toMap(t->t.getSimpleName(), Function.identity()));
    }

    public static Class getInterceptor(String name){
        return interceptorMap.get(name);
    }

    public static Class getAgentEntity(String name){
        return agentEntityMap.get(name);
    }

}
