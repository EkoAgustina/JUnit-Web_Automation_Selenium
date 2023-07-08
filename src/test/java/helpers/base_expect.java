package helpers;

import io.qameta.allure.Allure;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import static helpers.base_get.get_text;
import static helpers.base_screen.*;

public class base_expect {
    /*
        Used to verify if an element is displayed
     */
    public static String elment_displayed(String locator){
        Boolean displayed = base_find(locator).isDisplayed();
        try {
            if (displayed == true){
                return String.valueOf(displayed);
            }else {
                throw new RuntimeException(ANSI_RED+"Element Not Displayed!"+ANSI_RESET);
            }
        } catch (NoSuchElementException e){
            System.out.println(ANSI_RED+"Incompatible elements!"+ANSI_RESET);
            throw e;
        }
    }
    /*
        Used to compare data obtained from elements with test data
     */
    public static String equal_data (String locator, String test_data, String match, String condition){
        String equal;
        String element = get_text(locator);
        switch (match) {
            case "data":
                if (test_data == null || test_data.isEmpty()) {
                    throw new RuntimeException(ANSI_RED + "Test data not found!" + ANSI_RESET);
                } else {
                    equal = String.valueOf(element.equals(test_data));
                    switch (condition) {
                        case "equal":
                            if (equal.equals("true")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "'" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is equal with data '"+test_data+"'");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' not equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        case "not equal":
                            if (equal.equals("false")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is not equal with data '" + test_data + "' as expected" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is not equal with data '"+test_data+"' "+"as expected");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        default:
                            throw new RuntimeException(ANSI_RED+"Unrecognized condition"+ANSI_RESET);
                    }
                }
                break;
            case "regex":
                if (test_data == null || test_data.isEmpty()){
                    throw new RuntimeException(ANSI_RED+"Test data not found!"+ANSI_RESET);
                }
                else{
                    equal = String.valueOf(Pattern.matches(test_data,element));
                    switch (condition) {
                        case "equal":
                            if (equal.equals("true")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "'" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is equal with data '"+test_data+"'");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' not equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        case "not equal":
                            if (equal.equals("false")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is not equal with data '" + test_data + "' as expected" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is not equal with data '"+test_data+"' "+"as expected");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        default:
                            throw new RuntimeException(ANSI_RED + "Unrecognized condition" + ANSI_RESET);
                    }
                }
                break;
            default:
                throw new RuntimeException(ANSI_RED + "Parameters not recognized" + ANSI_RESET);
        }

        return null;

    }
}
