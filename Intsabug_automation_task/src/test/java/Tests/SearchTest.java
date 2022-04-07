package Tests;

import Pages.SearchPage;
import Utils.ExcelReader;
import Utils.ScreenshotTacker;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SearchTest extends TestBase {

    SearchPage searchPageObject;
    String testCaseName;


    /* Call the data from the excel file
       This data will represent the required test case data*/
    @DataProvider(name= "excelData")
    public Object[][] testCasesData() throws IOException {
        ExcelReader ER = new ExcelReader();
        String[][] array = ER.getExcelData(System.getProperty ("user.dir")+"\\data\\SearchTestCases.xlsx");
        String[][]testCases = new String[array.length-1][2];
        for (int i =0; i < testCases.length; i++){
            testCases[i][0] = array[i+1][0];
            testCases[i][1] = array[i+1][1];
        }

        return testCases;
    }


     /* Set our test method and put the required page methods in it*/
    @Test(alwaysRun=true, dataProvider="excelData")
    public void searchTest(String testCaseID, String Keyword){
        testCaseName = testCaseID;
        searchPageObject = new SearchPage(driver);
        searchPageObject.searchKeyWord(Keyword);
    }

    /*Create the method that will take screenshots from any failed test case
        and save it to a file with the time date and testcase name*/
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_YYYY_HH_mm");
        String strDate = dateFormat.format(date);
        String fileName = strDate + "_" + testCaseName;

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed");
            System.out.println("Taking ScreenShoot...");
            ScreenshotTacker.screenshot(fileName);
        }
    }

}
