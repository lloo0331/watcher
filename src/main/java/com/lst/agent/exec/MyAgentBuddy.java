package com.lst.agent.exec;

import com.lst.agent.config.entity.AgentChain;
import com.lst.agent.config.entity.AgentClass;
import com.lst.agent.config.entity.AgentElement;
import com.lst.agent.util.AgentHelp;
import com.lst.agent.util.ClassPathScanner;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
import java.util.Set;

/**
 * 程序入口
 * Created by li on 2018/1/4.
 */

public class MyAgentBuddy {

    private static Logger logger = LoggerFactory.getLogger(MyAgentBuddy.class);

    public static void premain(String agentArgs, Instrumentation inst) {

        logger.info("this is an perform monitor agent.");

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