package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;

import java.util.concurrent.TimeUnit;

//This is the Abstract TestClass where all test suites will inherit from
public class TestBase {
    //Declare the Driver
    public static WebDriver driver;

    /*Set Options for the used webdriver (Firefox is the default)
     & open the driver to access the pages with*/
    @BeforeSuite
    @org.testng.annotations.Parameters({"browser"})
    public void startDriver(@Optional("firefox") String browsername) {

        String path = "";
        if (browsername.equalsIgnoreCase("chrome")) {
            path= System.getProperty("user.dir")+
                    "\\drivers\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver",path );
            driver = new ChromeDriver();
        }else if (browsername.equalsIgnoreCase("firefox")) {
            path= System.getProperty("user.dir")+
                    "\\drivers\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver",path );
            driver = new FirefoxDriver();
        }else if (browsername.equalsIgnoreCase("ie")) {
            path= System.getProperty("user.dir")+
                    "\\drivers\\msedgedriver.exe";
            System.setProperty("webdriver.edge.driver",path );
            driver = new EdgeDriver();

        }

        driver.manage().window().maximize();
    }

    /* open the required page of the search engine before any test cases to make sure
     that the steps are the same for every test case*/
    @BeforeMethod
    public void navigateToURL() {
        String url= "https://www.google.com";
        driver.navigate().to(url);
        //Set implicit time for the driver to wait after opening the driver to allow
        //the page to be fully loaded
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }


    //Close the browser after finishing all test cases
    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }

}
