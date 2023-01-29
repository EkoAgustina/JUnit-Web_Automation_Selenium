package hooks;

import cucumber.steps.jobDetailSteps;
import helpers.base_screen;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;


import java.util.concurrent.TimeUnit;

import static helpers.base_screen.driver;
import static helpers.base_screen.takeScreenshotAllure;
import static java.lang.Thread.sleep;
import static helpers.base_screen.*;
import static mappings.mapper.file_path;


public class driverHooks {


    @Before
    public static void handlingBefore() throws InterruptedException {
       sleep(3);
    }

    @After
    public static void handlingAfter(Scenario scenario) throws InterruptedException {
        sleep(5);
        if (scenario.isFailed()){
            scenario.log("Scenario "+scenario.getName()+" "+scenario.getStatus());
            scenario.attach(takeScreenshotAllure(), "image/png", scenario.getName()+scenario.getStatus());
        }
        else{
            scenario.log(scenario.getName() +" "+scenario.getStatus());
        }
    }

}
