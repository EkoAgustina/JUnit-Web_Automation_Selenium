package cucumber.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/java/cucumber/features",
        glue={"cucumber.steps","hooks"},
        tags = "",
        plugin = {"json:target/cucumber.json","pretty","html:target/cucumber-reports","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = false,
        publish = true,
        snippets = CAMELCASE

)

public class TestRunner {
}
