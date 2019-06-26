package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.GetProperties;

public class BasePage {
    protected static WebDriver driver;
    protected static GetProperties properties = new GetProperties();

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyTitle(String title){
        return getTitle().equals(title);
    }
    public String getTitle(){ return this.driver.getTitle();}
}
