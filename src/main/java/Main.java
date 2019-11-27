import helper.prepairExcel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        prepairExcel.prepairExcel();

        //site, who there crawler should work
        String start_url = "https://www.travelontoast.de/";
        int depth = 0;
        new crawl(start_url, start_url, depth);
        //new crawl_iterative(start_url, start_url, depth);

        System.out.println("done");
    }
}
