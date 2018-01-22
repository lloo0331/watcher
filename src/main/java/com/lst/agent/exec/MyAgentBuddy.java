package com.lst.agent.exec;

import com.lst.agent.interceptor.*;
import com.lst.agent.match.InterceptorMatch;
import com.lst.agent.util.AgentHelp;
import com.lst.agent.util.ClassPathScanner;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by li on 2018/1/4.
 */

public class MyAgentBuddy {

    private static HashSet<String> classList = new HashSet<>();

    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("this is an perform monitor agent.");

        AgentBuilder.Transformer transformer = AgentHelp.createTransformer("StartEndInterceptor","aa","STARTS_WITH");

        AgentBuilder.Transformer transformer1 = AgentHelp.createTransformer("NodeInterceptor","any","any");

        AgentBuilder.Transformer transformer2 = AgentHelp.createTransformer("JsonInterceptor","toString","EQUALS_FULLY");

//        AgentBuilder.Transformer transformer1 = new AgentBuilder.Transformer() {
//            @Override
//            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
//                return builder
//                        .method(ElementMatchers.<MethodDescription>any()) // 拦截任意方法
//                        .intercept(MethodDelegation.to(NodeInterceptor.class)
//                        );
//            }
//        };

//        AgentBuilder builder = new AgentBuilder
//                .Default()
//                .type(ElementMatchers.nameStartsWith("demo.exec")) // 指定需要拦截的类
//                .transform(transformer)
//                .type(ElementMatchers.nameStartsWith("demo.all")) // 指定需要拦截的类
//                .transform(transformer1)
//                .type(ElementMatchers.nameStartsWith("com.lst.agent.entity")) // 指定需要拦截的类
//                .transform(transformer2)
//                .with(AgentHelp.getDefaultListener());


        AgentBuilder builder = new AgentBuilder
                .Default()
                .type(AgentHelp.getMatcher("demo.exec","STARTS_WITH")) // 指定需要拦截的类
                .transform(transformer)
                .type(AgentHelp.getMatcher("demo.all","STARTS_WITH")) // 指定需要拦截的类
                .transform(transformer1)
                .type(AgentHelp.getMatcher("com.lst.agent.entity","STARTS_WITH")) // 指定需要拦截的类
                .transform(transformer2)
                .with(AgentHelp.getDefaultListener());

        //这个是在Class加载的时候进行检测的.如果某个类没有使用到,则不会修改到

        builder.installOn(inst);

        scan();

    }

    /**
     * 扫描加载类,这个用于耗时统计那个功能,先加载可以有效的减少耗时统计的误差
     */
    public static void scan(){
        Set<Class> set1 =  ClassPathScanner.scan("com.lst.agent.entity",true,false,false,null);
        Set<Class> set2 =  ClassPathScanner.scan("demo",true,false,false,null);

        for(Class cls:set1){
            try {
                cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(Class cls:set2){
            try {
                cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}