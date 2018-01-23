package com.lst.agent.exec;

import com.lst.agent.interceptor.NodeInterceptor;
import com.lst.agent.util.AgentHelp;
import com.lst.agent.util.ClassPathScanner;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by li on 2018/1/4.
 */

public class MyAgentBuddyDemo {

    private static HashSet<String> classList = new HashSet<>();

    public static void test(String agentArgs, Instrumentation inst) {

        System.out.println("this is an perform monitor agent.");


        AgentBuilder.Transformer transformer1 = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.<MethodDescription>any()) // 拦截任意方法
                        .intercept(MethodDelegation.to(NodeInterceptor.class)
                        );
            }
        };

        AgentBuilder builder = new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("demo.all")) // 指定需要拦截的类
                .transform(transformer1)
                .type(ElementMatchers.nameStartsWith("com.lst.agent.entity")) // 指定需要拦截的类
                .transform(transformer1)
                .with(AgentHelp.getDefaultListener());

        //这个是在Class加载的时候进行检测的.如果某个类没有使用到,则不会修改到
        builder.installOn(inst);

    }


}