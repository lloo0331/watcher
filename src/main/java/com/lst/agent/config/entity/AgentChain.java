package com.lst.agent.config.entity;
/**
 * Created by li on 2018/1/22.
 */

import com.alibaba.fastjson.JSON;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.ArrayList;
import java.util.List;

public class AgentChain {

    List<AgentElement> list = new ArrayList<>();

    public void exce(AgentBuilder builder){
        for(AgentElement e:list){
            e.exec(builder);
        }
    }

    public void setList(List<AgentElement> list) {
        this.list = list;
    }

    public List<AgentElement> getList() {
        return list;
    }

    public void addAgentElement(AgentElement element){
        list.add(element);
    }

}
