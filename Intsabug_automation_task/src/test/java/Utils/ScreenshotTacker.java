package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

import static Tests.TestBase.driver;

/* This is a class to take a screenshot for the driver and save it as a png file*/

public class ScreenshotTacker {

    public static void screenshot (String filename) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String pathName = System.getProperty("user.dir")+"\\outputs\\screenshots\\"+ filename +  ".png";
            FileHandler.copy(source, new File(pathName));
            System.out.println("ScreenShot Taken");
        } catch (Exception e) {

            System.out.println( "Exception while taking screenshot "+ e.getMessage());
        }

    }

}
