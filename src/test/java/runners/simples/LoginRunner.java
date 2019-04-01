package runners.simples;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.TestContext;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps"},
        tags = {"@Login"},
        format = {
                "pretty",
                "html:reports/cucumber-reports/cucumber-pretty",
                "json:reports/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        strict = false
)
public class LoginRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    private TestContext context;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        this.context = new TestContext();
        this.driver = this.context.getWebDriverManager().getDriver();
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        this.testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return this.testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        this.context.getWebDriverManager().quitDriver();
        this.testNGCucumberRunner.finish();
    }
}
