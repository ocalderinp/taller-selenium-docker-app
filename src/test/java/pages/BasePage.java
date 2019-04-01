package pages;

import org.openqa.selenium.WebDriver;
import utils.GetProperties;

public class BasePage {
    protected static WebDriver driver;
    protected static GetProperties properties = new GetProperties();

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    public boolean verifyTitle(String title){
        return getTitle().equals(title);
    }
    public String getTitle(){ return this.driver.getTitle();}
}
