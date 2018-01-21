package com.lst.agent.exec;

import com.lst.agent.interceptor.*;
import com.lst.agent.util.ClassPathScanner;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
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

        //ByteBuddyAgent.install();

        System.out.println("this is an perform monitor agent.");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.nameStartsWith("aa")) // 拦截任意方法
                        .intercept(MethodDelegation.to(StartEndInterceptor.class)); // 委托
            }
        };

        AgentBuilder.Transformer transformer1 = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.<MethodDescription>any()) // 拦截任意方法
                        .intercept(MethodDelegation.to(NodeInterceptor.class)
                        );
            }
        };

        AgentBuilder.Transformer transformer2 = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.named("toString")) // 拦截toString方法
                        .intercept(MethodDelegation.to(JsonInterceptor.class)); // 委托
            }
        };


        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                //System.out.println("onTransformation:"+typeDescription.getTypeName()+":"+dynamicType.toString());
                System.out.println("onTransformation:"+typeDescription.getTypeName());
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
                //System.out.println("onComplete:"+typeName+";load="+loaded);

            }

        };


        AgentBuilder builder = new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("demo.exec")) // 指定需要拦截的类
                .transform(transformer)
                .type(ElementMatchers.nameStartsWith("demo.all")) // 指定需要拦截的类
                .transform(transformer1)
                .type(ElementMatchers.nameStartsWith("com.lst.agent.entity")) // 指定需要拦截的类
                .transform(transformer2)
                .with(listener);

        //这个是在Class加载的时候进行检测的.如果某个类没有使用到,则不会修改到

        builder.installOn(inst);



    }

    public static void load(){
        for(String className:classList){
            try {
                System.out.println("load:"+className);
                Thread.currentThread().getContextClassLoader().loadClass(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        classList.clear();
    }



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