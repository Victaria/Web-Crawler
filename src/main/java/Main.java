import helper.prepairExcel;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        File file = prepairExcel.prepairExcel();

        //site, who there crawler should work
        //crawl.test();

        //collect data
        coll_list.test(file);
        texts_un.test(file);
        texts_sum.test(file);

        System.out.println("done");
    }
}
