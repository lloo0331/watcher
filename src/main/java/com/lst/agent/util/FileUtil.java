package com.lst.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by world3-007 on 2018/1/23.
 */
public class FileUtil {

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
            e.printStackTrace();
        }
        a = s.toString();
        return a;
    }

}
