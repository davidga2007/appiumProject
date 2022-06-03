package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin= {"pretty"},
monochrome=true,
        features = "src/test/resources/features"
        ,glue={"stepDefinition"},
        		tags = "@tag1"
        )

public class runnerIT {

}
