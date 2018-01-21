package demo.exec;

import demo.all.Dispater;

/**
 * Created by li on 2018/1/7.
 */

public class Exec {

    public static void main(String args[])throws Exception{

        aa();

        Thread.sleep(1000);

        for(int i= 0;i<20;i++){
            aaa();

        }



    }


    public static void aa() throws Exception {
        int a = 2;
        Dispater dispater = new Dispater();
        dispater.init();
    }


    public static void aaa() throws Exception {
        int a = 2;
        Dispater dispater = new Dispater();
        dispater.init();
    }
}