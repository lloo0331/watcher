package demo.exec;

import demo.all.Dispater;

/**
 * Created by li on 2018/1/7.
 */

public class Exec {

    public static void main(String args[])throws Exception{

//        for(int i = 0;i<5;i++){
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try{
//                        aa();
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            });
//            thread.start();
//        }

//        Dispater dispater = new Dispater();
//        dispater.init();
//        aa();
//        aa();
        aa();
    }


    public static void aa() throws Exception {
        int a = 2;
        Dispater dispater = new Dispater();
        dispater.init();
    }

}