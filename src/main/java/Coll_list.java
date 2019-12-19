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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Coll_list {
    public static void main(String[] args){}

    public static void test(File sheetname) throws IOException {
        ArrayList<String> l = Coll_list.read("list.txt");
        Coll_list.coll(l, sheetname);
    }

    public static ArrayList<String> read(String file){
        ArrayList<String> l = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file))){
            while (reader.ready()){
                l.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static void coll (ArrayList<String> l, File sheetname) throws IOException{
        //access excel
        FileInputStream is = new FileInputStream(sheetname);
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        HSSFSheet sheet = workbook.getSheetAt(0);

        Cell cell;

        //get URL to analyse
        for ( int entry = 1; entry <= l.size() - 1; entry++) {
            Row row = sheet.createRow(entry);
            String url = l.get(entry);

            //get html
            try {
                String html = GetHTML.getHTML(url);
                Document doc = Jsoup.parse(html);
                int index = 0;//Spalten


                //set Url in Excel §1
                cell = row.createCell(index++, CellType.STRING);
                cell.setCellValue(url);

                //count the number of page pics §2
                Elements img = doc.select("img");
                int countpics = 0;
                for (Element e : img) {
                    countpics++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(countpics);

                //count texts §3
                Elements p = doc.select("p");
                int countp = 0;
                for (Element e : p) {
                    countp++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(countp);


                //titles h1 $4
                Elements h1 = doc.select("h1");
                int counth1 = 0;
                for (Element e : h1) {
                    counth1++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(counth1);

                //titles h2 §5
                Elements h2 = doc.select("h2");
                int counth2 = 0;
                for (Element e : h2) {
                    counth2++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(counth2);

                //titles h3 $6
                Elements h3 = doc.select("h3");
                int counth3 = 0;
                for (Element e : h3) {
                    counth2++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(counth3);

                //have comments §6
                Elements comments = doc.getElementsByClass("comments");
                Boolean c_comment = false;
                for (Element e : comments) {
                    c_comment = true;
                }
                cell = row.createCell(index++, CellType.BOOLEAN);
                cell.setCellValue(c_comment);

                //number comments  §7
                Elements c_ticker = doc.getElementsByClass("post-box-title");
                int comment = 0;
                for (Element e : c_ticker) {
                    for (Element r : e.children()) {
                        if (r.text().equals("Das könnte Dir auch gefallen")) {
                            // nothing
                        } else if (r.text().startsWith("keine")) {
                            comment = 0;
                        } else {
                            String counter = r.text().replaceAll(" Kommentare", "");
                            comment = Integer.parseInt(counter);
                            //System.out.println(comment);
                        }
                    }
                }

                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(comment);


                //number of articles , $8
                Elements article = doc.select("article");
                int count_articles = 0;
                for (Element d : article) {
                    count_articles++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(count_articles);

                //decide is a overview/ category site
                cell = row.createCell(index++, CellType.BOOLEAN);
                if (url.contains("category")) {
                    cell.setCellValue(true);
                } else {
                    cell.setCellValue(false);
                }

            } catch (Exception e) {
                //e.printStackTrace();
                //System.out.println(url + "broken");
            }
        }
        // write out
        FileOutputStream outFile = new FileOutputStream(sheetname);
        try {
            workbook.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("in file written(overview): " + sheetname.getAbsolutePath());
        is.close();
    }
}