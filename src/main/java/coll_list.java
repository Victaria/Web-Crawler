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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class coll_list{
    public static void main(String[] args){}

    public static void test() throws IOException {
        ArrayList<String> l = coll_list.read("list.txt");
        String sheetname = "data.xls";
        coll_list.coll(l, sheetname);
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

    public static void coll (ArrayList<String> l, String sheetname) throws IOException{
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
                String html = getHTML.getHTML(url);
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
                int count_comments = 0;
                for (Element e : comments) {
                    count_comments++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(count_comments);

                //number comments --- TODO try over children §7
                Elements ul = doc.getElementsByClass("ul");
                int comment = 0;
                for (Element e : ul) {
                    for (Element t : ul.parents()) {
                        for (Element d : t.parents()) {
                            if (d.toString().equals("comments")) {
                                comment++;
                            }
                        }
                    }
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(comment);


                //split articles and category's , i=8
                Elements article = doc.select("article");
                int count_articles = 0;
                for (Element e : article) {
                    count_articles++;
                }
                cell = row.createCell(index++, CellType.NUMERIC);
                cell.setCellValue(count_articles);

                //decide is a overview site
                if (!url.contains("category"))
                    //TODO collect text, because is a page
                    ;

                // TODO read date



            } catch (Exception e) {
                //e.printStackTrace();
                //System.out.println(url + "broken");
            }
        }
        // write out
        File file = new File(sheetname);

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("file written: " + file.getAbsolutePath());
        is.close();
    }
}