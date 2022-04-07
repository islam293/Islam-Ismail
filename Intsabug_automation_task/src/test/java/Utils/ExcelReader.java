package Utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/* This class is to read any required data from an excel file and save it in array to be
     used easily */

public class ExcelReader {

    //initialize element for the inputstream method
    static FileInputStream fis = null;

    // A method to find the required file for the input parameters
    //& assign it to the inputstream element
    public FileInputStream getFileInputStream(String path) {

        File srcFile = new File(path);

        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("  Test Data File not Found. terminatting proccess !! Check File Path of Test Data");
            System.exit(0);
        }
        return fis;
    }

    //read the data from the required file
    public String[][] getExcelData(String path) throws IOException {

        fis = getFileInputStream(path);
        XSSFWorkbook workbook= new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int totalRowsNumber = sheet.getLastRowNum()+1;
        int totalColumnsNumber = sheet.getRow(0).getLastCellNum();

       if (totalRowsNumber<2){
           System.out.println("No Test Cases found. Please add some testcases");
       }
        //put the data in a 2 dimensional array
        String[][] arrayExcelData = new String [totalRowsNumber][totalColumnsNumber];


        for (int i = 0; i < totalRowsNumber; i++) {
            for (int j = 0; j < totalColumnsNumber; j++) {
                XSSFRow row = sheet.getRow(i);
                if (row.getCell(j) == null){
                    arrayExcelData[i][j] = "";}
                else{
                    arrayExcelData[i][j] = row.getCell(j).toString();
                }
            }
        }
        workbook.close();
        return arrayExcelData;
    }
}