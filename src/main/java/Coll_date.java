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

public class Coll_date {


        public static void test(File sheetname) throws IOException {
            date(Coll_list.read("list.txt"), sheetname);
        }

        public static void date(ArrayList<String> list, File sheetname) throws IOException {
            //access excel
            FileInputStream is = new FileInputStream(sheetname);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet s4 = workbook.getSheetAt(3);

            Cell cell;
            int space = 1;

            //get URL to analyse
            for ( int entry = 1; entry <= list.size() - 1; entry++) {

                String url = list.get(entry);
                //get html
                try {
                    String html = GetHTML.getHTML(url);
                    Document doc = Jsoup.parse(html);

                    Elements posts = doc.getElementsByClass("item-related");
                    //System.out.println(posts.size());

                    //search for URL-Date reference's :set Url
                    for (Element post : posts){
                        for (Element i : post.children()){
                            for (Element k : i.children()){

                                Elements o = k.getElementsByAttribute("href");
                                String w = (o.select("a").attr("href"));
                                Row row = s4.createRow(space);

                                cell = row.createCell(0, CellType.STRING);
                                cell.setCellValue(w);

                                //: set date
                                //System.out.println(k.parent().lastElementSibling().text());
                                cell = row.createCell(1, CellType.STRING);
                                cell.setCellValue(k.parent().lastElementSibling().text());
                            }
                        }
                        space++;
                    }
                } catch (Exception ignored) { }
            }
            // write out
            FileOutputStream outFile = new FileOutputStream(sheetname);
            try {
                workbook.write(outFile);
            } catch (IOException ignored) { }
            System.out.println("in file written(date): " + sheetname.getAbsolutePath());
            is.close();
        }
    }