package com.lst.agent.config; /**
 * Created by li on 2018/1/22.
 */

import com.alibaba.fastjson.JSON;

public class Config {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
