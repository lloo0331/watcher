package com.lst.agent.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lst.agent.config.entity.*;
import com.lst.agent.match.MatchCenter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lst on 2018/1/22.
 */
public class AgentHelp {

    private static Logger logger = LoggerFactory.getLogger(AgentHelp.class);

    /**
     * 获得名称匹配
     * @param matchType
     * @return
     */
    public static StringMatcher.Mode getMode(String matchType){

        StringMatcher.Mode mode = StringMatcher.Mode.valueOf(matchType);

        return mode;

    }

    //创建代理链
    public static AgentChain createAgentChain(){
        String a = FileUtil.getString("config/config.json");
        JSONObject jsonObject = JSON.parseObject(a);
        AgentChain chain = new AgentChain();

        JSONArray array = jsonObject.getJSONArray("lists");
        for(int i = 0;i<array.size();i++){
            JSONObject obj = array.getJSONObject(i);
            try{
                Class<AgentElement> classes = MatchCenter.getAgentEntity(obj.getString("elementName"));
                if(classes!=null){
                    chain.addAgentElement(JSON.toJavaObject(obj,classes));
                }
            }catch (Exception e){
                logger.error("创建代理链异常",e);
            }
        }

        return chain;
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
                logger.info("onTransformation:"+typeDescription.getTypeName());
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
        Class classes = MatchCenter.getInterceptor(interceptorName);

        if(classes == null){
            RuntimeException e = new RuntimeException("没有找到interceptor");
            logger.error("onTransformation:"+e);
            throw e;
        }

        ElementMatcher.Junction matcher = AgentHelp.getMatcher(matchName,matchType);

        if(matcher == null){
            RuntimeException e = new RuntimeException("没有找到matcher");
            logger.error("onTransformation:"+e);
            throw e;
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
