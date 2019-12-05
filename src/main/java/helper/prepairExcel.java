package helper;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class prepairExcel {

    public static void prepairExcel() throws IOException {
        //create Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //create sheet
        HSSFSheet sheet = workbook.createSheet("all.xls");
        HSSFSheet s2 = workbook.createSheet("p.xls");

        Cell cell;
        Row row;

        //create row
        row = sheet.createRow(0);

        //write header
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("URL");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("pics");
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("p");
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("h1");
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("h2");
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("h3");
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("have comment field");
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("number of comments");
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("number linked articles");
        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("is a category");

        //texts
        row = s2.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("URL");

        for (int i = 1; i <= 132; i++){
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue("p" + i);
        }

        // write out
        File file = new File("data.xls");
        //file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
