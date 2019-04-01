package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {
    @FindBy(how = How.CSS, using = "input[placeholder='Username']")
    @CacheLookup
    private WebElement userInput;

    @FindBy(how = How.CSS, using = "input[placeholder='Password']")
    @CacheLookup
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Log in']")
    @CacheLookup
    private WebElement loginButton;

    private String badCredentialsMsgXPATH = "//div[contains(@class, 'alert-danger') and contains(., 'Bad credentials Please try again')]";

    private String url = properties.getString("URL");


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navegarAInicio() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void loginAs(String user, String password) {
        userInput.sendKeys(user);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public boolean msgBadCredentilasDisplayed() {
        return driver.findElement(By.xpath(badCredentialsMsgXPATH)).isDisplayed();
    }

    public void login() {
        loginAs("admin", "admin123");
    }
}
