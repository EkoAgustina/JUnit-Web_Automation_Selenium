package mappings;

import org.openqa.selenium.By;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class mapper {

    /*
        Used to map Element paths
     */
    public static String key_element(String element){
        String use_selector = "src/test/java/selector/"+element;
        return use_selector;
    }
    /*
        Used to map the path of Test Data
     */
    public static String key_data(String data){
        String use_data = "src/test/java/resources/"+data;
        return use_data;
    }
    /*
        Used to map Path File path
     */
    public static File file_path(String path){
        File base_path = new File(System.getProperty("user.dir"));
        File use_path = new File(base_path, path);
        return use_path;
    }
    /*
        Used to parse the Locator on Element
     */
    public static By locatorParser(String locator){
        By loc = null;
        if (locator.contains("By.id")) {
            loc = By.id(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
        }
        else if (locator.contains("By.name")){
            loc = By.name(locator.substring(locator.indexOf("\"") + 1,locator.length() - 2));
        }
        if (locator.contains("By.xpath")) {
            loc = By.xpath(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
        }
        return loc;
    }
    /*
        Used to read and parse YAML files.
        So that it can be used to put Locator, Element, Test Data and others
     */
    public static String LoadYaml(String selector, String element) {
        Map conf = new HashMap();
        Yaml yaml = new Yaml();
        String config   = conf.toString();

        try {
            InputStream stream = new FileInputStream(selector);

            conf = (Map) yaml.load(stream);
            config = (String) conf.get(element);

            if (conf == null || conf.isEmpty() == true) {
                throw new RuntimeException("Failed to read config file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No such file " + selector);
            throw new RuntimeException("No config file");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new RuntimeException("Failed to read config file");
        }

        return config;
    }


}
