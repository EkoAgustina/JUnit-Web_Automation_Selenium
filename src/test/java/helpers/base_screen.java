package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static mappings.mapper.file_path;
import static mappings.mapper.locatorParser;

public class base_screen {
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_RED = "\u001B[41m";

    static Properties prop=new Properties();
    public static WebDriver driver;
    public static String continuousIntegration = System.getenv("CI");

    public static void base_sleep(int duration) throws InterruptedException {
        TimeUnit.SECONDS.sleep(duration);
    }
    public static WebDriverWait wait (int period){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(period));
        return wait;
    }

    /*
      Used as a basic function for selecting browser drivers
    */
    public static WebDriver browserDriver(String browser) throws MalformedURLException {
        if (continuousIntegration != null && continuousIntegration == "true"){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPlatformName("linux");
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            driver = new RemoteWebDriver(new URL(browser),chromeOptions);
        }
        else{
            switch (browser){
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
                    driver = new ChromeDriver(chromeOptions);
                    file_path("chromedirver.exe").getAbsolutePath();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
                    driver = new EdgeDriver(edgeOptions);
                    file_path("msedgedriver.exe").getAbsolutePath();
                    break;
                case "headless":
                    ChromeOptions chromeHeadless = new ChromeOptions();
                    chromeHeadless.addArguments("--remote-allow-origins=*","ignore-certificate-errors","--headless");
                    driver = new ChromeDriver(chromeHeadless);
                    break;
                default:
                    throw new RuntimeException(ANSI_RED+"Your browser cannot support"+ANSI_RESET);
            }
        }
        return driver;
    }
    /*
        Used as a basic function to search for Elements
    */
    public static WebElement base_find (String locator){
        WebElement selenium_element = null;

        try {
            WebElement webElement = driver.findElement(locatorParser(prop.getProperty("locator", locator)));
            selenium_element = (WebElement) wait(10).until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException e){
            System.out.println(ANSI_RED+"Elements  doesn't exist!"+ANSI_RESET);
            throw e;
        }
        return selenium_element;
    }
    /*
        Used as a basic function for taking screenshots
        This function is only for hooks
     */
    public static byte[] takeScreenshotAllure(){
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
    /*
       Used as a basic function to take a screenshot and then save it in your project folder
    */
    public static File captureScreen(String screenshotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            if(Files.exists(Paths.get("./user_screenshots/" + screenshotName + ".png"))){
                FileUtils.copyFile(scrFile, new File("./user_screenshots/"+screenshotName+"1"+".png"));
            }
            else {
                FileUtils.copyFile(scrFile, new File("./user_screenshots/"+screenshotName+".png"));
            }
        } catch (Exception e){
            throw new RuntimeException("Failed to capture screen: "+e.getMessage());
        }
        return null;
    }
}
