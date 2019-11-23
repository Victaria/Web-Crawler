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

    public prepairExcel() throws IOException {
        //create Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //create sheet
        HSSFSheet sheet = workbook.createSheet("data.xls");

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

        // write out
        File file = new File("data.xls");
        //file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
