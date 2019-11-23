import helper.prepairExcel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //prepare excel access
        prepairExcel.prepairExcel();
        //listing all URL`s
        String start_url = "https://www.travelontoast.de/";

        Bot bot = new Bot(start_url);
        bot.start();

        //listing depth of USl's
        BasicWebCrawler bwc = new BasicWebCrawler();
        bwc.start();

    }
}
