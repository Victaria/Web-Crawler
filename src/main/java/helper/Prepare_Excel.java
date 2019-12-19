package helper;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Prepare_Excel {

    public static File prepairExcel() throws IOException {
        //create Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //create sheet
        HSSFSheet sheet = workbook.createSheet("overview");
        HSSFSheet s2 = workbook.createSheet("p_un");
        HSSFSheet s3 = workbook.createSheet("p_sum");
        HSSFSheet s4 = workbook.createSheet("date");

        Cell cell;
        Row row;

        //create row
        row = sheet.createRow(0);

        //write header for overview
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
        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("is a category");

        //texts_un
        row = s2.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("URL");

        for (int i = 1; i <= 132; i++){
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue("p" + i);
        }

        //texts_sum
        row = s3.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("URL");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("p");

        //date
        row = s4.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("URL");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Date");

        // write out
        LocalDate now = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        //System.out.println(now.format(df));
        File file = new File(now.format(df) + ".xls");
        //file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
        return file;
    }
}
