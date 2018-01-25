package com.lst.agent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by world3-007 on 2018/1/23.
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String getString(String path){
        String a = null;
        InputStream in = AgentHelp.class.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder s = new StringBuilder();

        try {
            while((a = reader.readLine())!=null){
                s.append(a);
            }
        } catch (IOException e) {
            logger.error("文件读取异常",e);
        }
        a = s.toString();
        return a;
    }

}
