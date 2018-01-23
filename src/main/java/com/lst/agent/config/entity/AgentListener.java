package com.lst.agent.config.entity;

import com.lst.agent.util.AgentHelp;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * Created by li on 2018/1/22.
 */
public class AgentListener extends AgentElement{

    public AgentBuilder exec(AgentBuilder builder){
        builder = builder.with(AgentHelp.getDefaultListener());
        return builder;
    }

}
