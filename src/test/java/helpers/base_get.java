package helpers;

import static helpers.base_screen.*;

public class base_get {
    /*
        Used as a basic function to get data from an element
     */
    public static String get_text(String locator){
        String text;
        try {
            text = base_find(locator).getText();
            if (text == null || text.isEmpty() == true){
                throw new RuntimeException(ANSI_RED+"Text not found!"+ANSI_RESET);
            }
            else {
                return text;
            }
        } catch (Exception e){
            System.out.println(ANSI_RED+"Incompatible elements!"+ANSI_RESET);
            throw e;
        }
    }
}
