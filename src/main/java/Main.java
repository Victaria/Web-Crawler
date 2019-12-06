import helper.prepairExcel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        prepairExcel.prepairExcel();

        //site, who there crawler should work
        //crawl.test();

        //collect data
        coll_list.test();
        texts_un.test();
        texts_sum.test();

        System.out.println("done");
    }
}
