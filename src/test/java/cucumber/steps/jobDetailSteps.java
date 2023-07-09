package cucumber.steps;

import helpers.base_click;
import helpers.base_expect;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static helpers.base_expect.equal_data;
import static helpers.base_fill.fill;
import static helpers.base_screen.*;
import static java.lang.Thread.sleep;
import static mappings.mapper.*;

public class jobDetailSteps {
    WebDriver myBrowser;
    String path_screenshot;

    /*
       Used to provide actions open url
   */
    @Given("^User open \"(.*)\"$")
    public void userOpenWith(String url) throws Exception {
        String browser = System.getProperty("browser","");
        if(continuousIntegration != null && continuousIntegration == true){
            if (url == null || url.isEmpty() == true){
                throw new RuntimeException(ANSI_RED+"Url not found!"+ANSI_RESET);
            }
            else{
                myBrowser = browserDriver("http://localhost:"+browser+"/wd/hub");
                myBrowser.manage().window().maximize();
                myBrowser.get(url);
                sleep(3);
            }
        }
        else{
            if (url == null || url.isEmpty() == true){
                throw new RuntimeException(ANSI_RED+"Url not found!"+ANSI_RESET);
            }
            else{
                myBrowser = browserDriver(browser);
                myBrowser.manage().window().maximize();
                myBrowser.get(url);
                sleep(3);
            }
        }
    }
    /*
         Used to provide waiting time
     */
    @And("^Wait (.*) seconds$")
    public void userWaitSeconds(int time) throws InterruptedException {

        try {
            base_sleep(time);
        } catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step is failed! "+"Your time: '"+ time+ANSI_RESET+ " and your original error: '"+e.getMessage()+"'"+ANSI_RESET);
        }
    }
    /*
        Used to provide a Click action on an Element
     */
    @And("^User click \"(.*)\"$")
    public void userClick(String element) throws FileNotFoundException {
        try{
            base_click.click(key_element(element));
        } catch (Exception e) {
            throw new RuntimeException(ANSI_RED + "Step failed with original error: " + ANSI_RESET + e.getMessage());
        }
    }
    /*
        Used to give the Element a fill action
    */
    @And("^Fill in \"(.*)\" with \"(.*)\"$")
    public void userFillsInWith(String element, String test_data) {
        try{
            fill(key_element(element),key_data(test_data));
        }catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to verify the element is displayed
     */
    @Then("^Element \"(.*)\" will be displayed$")
    public void verifyElementWillBeDisplayed(String element){
       try{
           base_expect.elment_displayed(key_element(element));
       }catch (Exception e){
           throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
       }
    }
    /*
       Used to verify the data obtained from the elements then compared with the test data
    */
    @Then("^Element \"(.*)\" is (equal|not equal) with (data|regex) \"(.*)\"$")
    public void VerifyValueIsWithData(String element, String condition,String match, String test_data ) {
        try {
            equal_data(key_element(element),key_data(test_data),match,condition);
        }
        catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to take screenshots then save in your project folder and screenshots will be displayed in the Allure Report
     */
    @Then("^User take screenshot with file name \"(.*)\"$")
    public void userTakesScreenshotWithFileName(String screenshotName) throws IOException {
        try {
            path_screenshot = String.valueOf(captureScreen(screenshotName));
            Allure.addAttachment("Your screenshot", new ByteArrayInputStream(takeScreenshotAllure()));
        }
        catch (Exception e){
            throw new RuntimeException(ANSI_RED+"You failed to take a screenshot!"+ANSI_RESET+" and your original error: '"+e.getMessage()+ANSI_RESET);
        }

    }
}
