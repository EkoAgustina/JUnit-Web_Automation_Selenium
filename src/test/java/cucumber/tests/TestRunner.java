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
        monochrome = true,
        publish = true,
        snippets = CAMELCASE

)
//mvn test -Dcucumber.options="src/test/java/cucumber/tests/TestRunner.java" -Dcucumber.filter.tags="@withEdge"
public class TestRunner {
}
