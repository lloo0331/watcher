package demo.all;

/**
 * Created by li on 2018/1/7.
 */

public class DemoHandler {

    public int demo() throws Exception {

        //Thread.sleep(500);
        DemoService service = new DemoService();
        return service.getCount();
    }


    public int demo2()throws Exception{

        //Thread.sleep(500);
        DemoService service = new DemoService();
        service.getCount();

        return service.getCount1();

    }

    public int demo3()throws Exception{
        return 1;
    }

}
