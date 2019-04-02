package runners.simples;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps"},
        tags = {"@Employee"},
        format = {
                "pretty",
                "html:reports/cucumber-reports/cucumber-pretty",
                "json:reports/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        strict = false
)
public class EmployeeRunner {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUpClass(@Optional  String browser) {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        System.setProperty("test.browser", browser != null? browser : "chrome");
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
        this.testNGCucumberRunner.finish();
    }
}
