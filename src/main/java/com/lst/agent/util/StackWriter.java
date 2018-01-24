package com.lst.agent.util;
/**
 * Created by li on 2018/1/24.
 */

import com.alibaba.fastjson.JSON;

public class StackWriter {

    public void write(Advice advice){

        String head = "";

    }

    public void close(){


    }


//    def write(context: Context) {
//        // TODO Avoid duplicated stack
//
//        val head = "%1$s.%2$s%3$s call by thread [%4$s]"
//                .format(context.className, context.methodName, context.descriptor, context.thread.getName)
//
//        writer.write(head)
//        writer.newLine()
//        context.stack foreach { s => writer.write("\t" + s); writer.newLine() }
//        writer.newLine()
//    }
//
//    def close() {
//        try {writer.close()} catch {case _ => }
//    }
}
