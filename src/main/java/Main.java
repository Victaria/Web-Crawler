import helper.Prepare_Excel;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        File file = Prepare_Excel.prepairExcel();

        //site, who there crawler should work
        //crawl.test();

        //collect data
        Coll_list.test(file);
        Texts_un.test(file);
        Texts_sum.test(file);

        System.out.println("done");
    }
}
