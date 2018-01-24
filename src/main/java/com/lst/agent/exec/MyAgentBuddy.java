package com.lst.agent.exec;

import com.lst.agent.config.entity.AgentChain;
import com.lst.agent.config.entity.AgentClass;
import com.lst.agent.config.entity.AgentElement;
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
 * 程序入口
 * Created by li on 2018/1/4.
 */

public class MyAgentBuddy {

    private static HashSet<String> classList = new HashSet<>();

    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("this is an perform monitor agent.");

        AgentBuilder builder = new AgentBuilder.Default();//创建默认代理链

        AgentChain chain = AgentHelp.createAgentChain();//读json文件,装配代理链

        for(AgentElement e:chain.getList()){
            //System.out.println(e);
            builder = e.exec(builder);
        }//执行代理链

        //这个是在Class加载的时候进行检测的.如果某个类没有使用到,则不会修改到
        builder.installOn(inst);

        //提前加载类
        loadClass(chain);

    }

    /**
     * 提前加载类,这个用于耗时统计那个功能,先加载可以有效的减少耗时统计的误差
     * @param chain
     */
    private static void loadClass(AgentChain chain) {
        for(AgentElement element:chain.getList()){
            if(element instanceof AgentClass){
                AgentClass classes = (AgentClass)element;
                if(classes.isScan()){
                    Set<Class> set1 = ClassPathScanner.scan(classes.getClassName(),true,false,false,null);
                    for(Class cls:set1){
                        try {
                            cls.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}