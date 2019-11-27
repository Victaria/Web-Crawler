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

public class collect {
    public static void main(String[] args) throws IOException {
        //test function
        String start_url = "https://www.travelontoast.de/tokio-tipps-sehenswuerdigkeiten/#Tokio_Tipps_zur_Reiseplanung";
        collect analyse = new collect();
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

        //set Url in Excel
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(url);

        //decide is a overview site
        if (url.contains("category")){
            //TODO is a overview site
        }else{
            //TODO is it not
        }

        //count the number of page pics
        Elements img = doc.select("img");
        int countpics = 0;
        for (Element e: img){
            countpics++;
        }
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(countpics);

        //count number of article
        Elements article = doc.select("article");
        int countart = 0;
        for (Element e: article){
            countart++;
        }

        //count words
        Elements p = doc.select("p");
        int countp = 0;
        for (Element e: p){
            countp++;
        }
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(countp);

        //titles
        Elements h1 = doc.select("h1");
        int counth1 = 0;
        for (Element e: h1){
            counth1++;
        }
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(counth1);

        Elements h2 = doc.select("h2");
        int counth2 = 0;
        for (Element e: h2){
            counth2++;
        }
        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue(counth2);

        Elements h3 = doc.select("h3");
        int counth3 = 0;
        for (Element e: h3){
            counth2++;
        }
        cell = row.createCell(5, CellType.NUMERIC);
        cell.setCellValue(counth3);

        //count comment
        Elements comment = doc.getElementsByClass("comments");
        int countcomment = 0;
        for (Element e: comment){
            countcomment++;
        }
        cell = row.createCell(6, CellType.NUMERIC);
        cell.setCellValue(countcomment);


        // write out
        File file = new File(sheetname);
        //file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        //System.out.println("file: " + file.getAbsolutePath());

        //cleaning
        is.close();
    }
}
