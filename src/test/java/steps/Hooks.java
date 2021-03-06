package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContext;

public class Hooks {
    private TestContext context;
    private WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(Hooks.class);

    public Hooks(TestContext context){
        this.context = context;
    }

    @Before
    public void setUp(Scenario scenario){
        LOGGER.info("Starting scenario: " + scenario.getName());
        this.driver = this.context.getWebDriverManager().getDriver();
    }

    @After
    public void tearDown(Scenario scenario){
        LOGGER.info("Finishing scenario: " + scenario.getName());
        if(scenario.isFailed()){
            try {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        this.context.getWebDriverManager().quitDriver();
    }
}
