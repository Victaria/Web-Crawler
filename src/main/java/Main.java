import helper.prepairExcel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        prepairExcel.prepairExcel();

        //site, who there crawler should work
        String start_url = "https://www.travelontoast.de/";

        //listing all URL`s
        Bot bot = new Bot(start_url);
        bot.start();

        //listing depth of URL`s
        BasicWebCrawler bwc = new BasicWebCrawler();
        bwc.start();

        System.out.println("finish");
    }
}
