package com.lst.agent.config.entity;

import com.lst.agent.util.AgentHelp;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * Created by li on 2018/1/22.
 * 代理元素
 */

public abstract class AgentElement {

    protected String elementName;

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementName() {
        return elementName;
    }

    public abstract AgentBuilder exec(AgentBuilder builder);

}
