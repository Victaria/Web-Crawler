import helper.getHTML;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class coll_uniq {
    public static void main(String[] args) throws IOException {
        //test function
        String start_url = "https://www.travelontoast.de/tokio-tipps-sehenswuerdigkeiten/#Tokio_Tipps_zur_Reiseplanung";
        coll_uniq analyse = new coll_uniq();
        analyse.analyse(start_url, 1, "data.xls");
    }

    void analyse(String url, int index, String sheetname) throws IOException {
        //get html
        String html = getHTML.getHTML(url);
        Document doc = Jsoup.parse(html);

        //access excel
        FileInputStream is = new FileInputStream(sheetname);
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell;
        Row row;
        row = sheet.createRow(index);
        int i = 0;

        //set Url in Excel
        cell = row.createCell(i++, CellType.STRING);
        cell.setCellValue(url);

        //count the number of page pics
        Elements img = doc.select("img");
        int countpics = 0;
        for (Element e: img){
            countpics++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(countpics);


        //count texts
        Elements p = doc.select("p");
        int countp = 0;
        for (Element e: p){
            countp++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(countp);


        //titles h1
        Elements h1 = doc.select("h1");
        int counth1 = 0;
        for (Element e: h1){
            counth1++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(counth1);

        //titles h2
        Elements h2 = doc.select("h2");
        int counth2 = 0;
        for (Element e: h2){
            counth2++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(counth2);

        //number of h3 titles
        Elements h3 = doc.select("h3");
        int counth3 = 0;
        for (Element e: h3){
            counth2++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(counth3);


        //have comments i=6
        Elements comments = doc.getElementsByClass("comments");
        int count_comments = 0;
        for (Element e: comments){
            count_comments++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(count_comments);

        //number comments --- TODO try over children
        Elements ul = doc.getElementsByClass("ul");
        int comment = 0;
        for (Element e: ul) {
            for (Element t: ul.parents()) {
                for (Element d: t.parents()) {
                    if (d.toString().equals("comments")){
                        comment++;
                    }
                }
            }
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(comment);


        //split articles and category's , i=8
        Elements article = doc.select("article");
        int count_articles = 0;
        for (Element e: article){
            count_articles++;
        }
        cell = row.createCell(i++, CellType.NUMERIC);
        cell.setCellValue(count_articles);

                //decide is a overview site
        if (!url.contains("category"))
            //TODO collect text, because is a page
            ;


        //read date


        // write out
        File file = new File(sheetname);
        //file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        //System.out.println("file: " + file.getAbsolutePath());
        System.out.println(url);
        //cleaning
        is.close();
    }
}
