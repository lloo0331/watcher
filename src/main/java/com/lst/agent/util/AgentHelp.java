package com.lst.agent.util;

import com.lst.agent.match.InterceptorMatch;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.BooleanMatcher;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.NameMatcher;
import net.bytebuddy.matcher.StringMatcher;
import net.bytebuddy.utility.JavaModule;

/**
 * Created by lst on 2018/1/22.
 */
public class AgentHelp {

    /**
     * 获得名称匹配
     * @param matchType
     * @return
     */
    public static StringMatcher.Mode getMode(String matchType){

        StringMatcher.Mode mode = StringMatcher.Mode.valueOf(matchType);

        return mode;


    }

    /**
     * 获得匹配类型
     * @param name
     * @param matchType
     * @param <T>
     * @return
     */
    public static <T extends NamedElement> ElementMatcher.Junction<T> getMatcher(String name,String matchType) {

        if(matchType.equals("any")){
            return new BooleanMatcher(true);
        }

        StringMatcher.Mode mode = getMode(matchType);
        return new NameMatcher(new StringMatcher(name, mode));
    }

    public static AgentBuilder.Listener getDefaultListener(){
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
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
        return listener;
    }

    public static AgentBuilder.Listener getDefaultListener1(){
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                System.out.println("11111111111onTransformation:"+typeDescription.getTypeName());
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
        return listener;
    }

    public static AgentBuilder.Transformer createTransformer(String interceptorName,String matchName,String matchType){
        Class classes = InterceptorMatch.getClass(interceptorName);

        if(classes == null){
            throw new RuntimeException("没有找到interceptor");
        }

        ElementMatcher.Junction matcher = AgentHelp.getMatcher(matchName,matchType);

        if(matcher == null){
            throw new RuntimeException("没有找到matcher");
        }

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(matcher) // 拦截任意方法
                        .intercept(MethodDelegation.to(classes)); // 委托
            }
        };
        return transformer;
    }
}