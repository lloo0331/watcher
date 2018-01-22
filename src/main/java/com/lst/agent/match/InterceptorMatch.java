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
public class InterceptorMatch {

    public static Map<String,Class> interceptorMap = new HashMap<>();

    static {
        Set<Class> set1 =  ClassPathScanner.scan("com.lst.agent.interceptor",true,false,false,null);

        interceptorMap = set1.stream().collect(Collectors.toMap(t->t.getSimpleName(), Function.identity()));
    }

//    public static void init(){
//
//
//
//    }

    public static Class getClass(String name){
        return interceptorMap.get(name);
    }

}
