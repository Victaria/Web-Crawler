import helper.prepairExcel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        prepairExcel.prepairExcel();

        //site, who there crawler should work
        //crawl.test();

        coll_list.test();

        System.out.println("done");
    }
}
