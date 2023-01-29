package helpers;

import org.openqa.selenium.ElementClickInterceptedException;

import static helpers.base_screen.*;

public class base_fill {

    public static void fill(String locator, String test_data){
        try {
            base_find(locator).sendKeys(test_data);
        } catch (Exception e){
            System.out.println(ANSI_RED+"Failed to fill!"+ANSI_RESET);
            throw e;
        }
    }
}
