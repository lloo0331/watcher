package demo.start;

import demo.service.DemoHandler;

/**
 * Created by li on 2018/1/7.
 */


public class Dispater {


    public void init() throws Exception{

        DemoHandler handler = new DemoHandler();
        handler.demo();

    }

}
