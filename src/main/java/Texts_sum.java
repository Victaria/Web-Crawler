import helper.GetHTML;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Texts_sum {

    public static void test(File sheetname) throws IOException {
        p(Coll_list.read("list.txt"), sheetname);
    }

    public static void p(ArrayList<String> list, File sheetname) throws IOException {
        //access excel
        FileInputStream is = new FileInputStream(sheetname);
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        HSSFSheet s3 = workbook.getSheetAt(2);

        Cell cell;

        //get URL to analyse
        for ( int entry = 1; entry <= list.size() - 1; entry++) {
            Row row = s3.createRow(entry);
            String url = list.get(entry);

            //get html
            try {
                String html = GetHTML.getHTML(url);
                Document doc = Jsoup.parse(html);

                //set Url in Excel §1
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(url);

                //set texts §n
                ArrayList<String> cache = new ArrayList<String>();
                Elements p = doc.select("p");
                for (Element e : p) {
                    if (!e.children().toString().contains("href")
                            && !e.children().toString().contains("span"))
                    {
                        cache.add(e.text());
                    }
                }
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(cache.toString());

            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        // write out
        FileOutputStream outFile = new FileOutputStream(sheetname);
        try {
            workbook.write(outFile);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        System.out.println("in file written(p_sum): " + sheetname.getAbsolutePath());
        is.close();
    }
}
