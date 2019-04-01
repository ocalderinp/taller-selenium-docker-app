package utils.webdriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.GetProperties;
import utils.datatypes.BrowserType;
import utils.datatypes.EnvironmentType;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private WebDriver driver = null;
    private static final Logger LOGGER = Logger.getLogger(WebDriverManager.class);
    private static GetProperties properties = new GetProperties();
    private static String browser = properties.getString("BROWSER").toUpperCase();
    private static String environment = properties.getString("ENVIRONMENT").toUpperCase();
    private static URL hub;

    public WebDriverManager(){}

    public WebDriver getDriver(){
        if(getEnvironment().equals(EnvironmentType.REMOTE)){
            try {
                hub = new URL(properties.getString("HUB"));
            } catch (MalformedURLException e) {
                LOGGER.error("Invalid hub url: " + hub.toString());
            }
        }
        if(driver == null){
            driver = createDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public EnvironmentType getEnvironment(){
        return EnvironmentType.valueOf(environment);
    }

    public BrowserType getBrowserType(){
        return BrowserType.valueOf(browser);
    }

    private WebDriver createDriver() {
        LOGGER.info("OS detected: " + System.getProperty("os.name").toUpperCase());
        switch (getBrowserType()){
            case CHROME:
                driver = (getEnvironment().equals(EnvironmentType.REMOTE))? new RemoteWebDriver(hub, getChromeOptions()) :
                        new ChromeDriver(getChromeOptions());
                break;
            case FIREFOX:
                driver = (getEnvironment().equals(EnvironmentType.REMOTE))? new RemoteWebDriver(hub, new FirefoxOptions()) :
                        new FirefoxDriver();
                break;
        }
        if(getEnvironment().equals(EnvironmentType.REMOTE)){
            LOGGER.info("Executing test in remote environment");
            LOGGER.info("Connecting to hub url: " + hub.toString());
        } else LOGGER.info("Executing test in local environment");
        LOGGER.info("Browser selected: " + browser.toUpperCase());
        return driver;
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("disable-infobars");
        options.addArguments("headless");
        return options;
    }

    public void quitDriver(){
        if(driver != null){
            LOGGER.info("Ending execution");
            driver.quit();
        }
    }
}
