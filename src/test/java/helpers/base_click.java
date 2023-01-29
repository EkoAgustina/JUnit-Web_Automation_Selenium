package helpers;

import org.openqa.selenium.ElementClickInterceptedException;

import static helpers.base_screen.*;

public class base_click {
    /*
      Used as a base function to provide a Click action on an Element
   */
    public static void click(String locator) throws InterruptedException {
        try {
            base_sleep(3);
            base_find(locator).click();
        } catch (Exception e){
            System.out.println(ANSI_RED+"Elements cannot be clicked!"+ANSI_RESET);
            throw e;
        }
    }
}
