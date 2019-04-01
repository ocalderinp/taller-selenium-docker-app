package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EmployeePage extends BasePage {
    @FindBy(how = How.XPATH, using = "//h4[contains(text(), 'Add Employee')]")
    @CacheLookup
    private WebElement labelAddEmployee;

    @FindBy(how = How.ID, using = "name")
    @CacheLookup
    private WebElement nameInput;

    @FindBy(how = How.ID, using = "address")
    @CacheLookup
    private WebElement addressInput;

    @FindBy(how = How.ID, using = "city")
    @CacheLookup
    private WebElement cityInput;

    @FindBy(how = How.ID, using = "state")
    @CacheLookup
    private WebElement stateInput;

    @FindBy(how = How.ID, using = "postcode")
    @CacheLookup
    private WebElement postcodeInput;

    @FindBy(how = How.ID, using = "email")
    @CacheLookup
    private WebElement emailInput;

    @FindBy(how = How.ID, using = "phoneNumber")
    @CacheLookup
    private WebElement phoneNumberInput;

    @FindBy(how = How.ID, using = "addButton")
    @CacheLookup
    private WebElement addButton;

    @FindBy(how = How.CSS, using = "h4 span" )
    @CacheLookup
    private WebElement linkLogout;

    private By msgEmployeeAddedLocator = By.id("success-alert");

    public EmployeePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean loggedInAsUser(String user) {
        return driver.findElement(By.xpath("//span[contains(text(), '" + user + "')]")).isDisplayed();
    }


    public boolean atAddEmployeePage() {
        return labelAddEmployee.isDisplayed();
    }

    public void logout() {
        linkLogout.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void addEmployee(String name, String address, String city, String estado, String zipcode, String email, String telefono) {
        nameInput.sendKeys(name);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        stateInput.sendKeys(estado);
        postcodeInput.sendKeys(zipcode);
        emailInput.sendKeys(email);
        phoneNumberInput.sendKeys(telefono);
        addButton.click();
    }

    public boolean isEmployeeAddedSuccesfuly() {
        WebElement msgEmployeeAdded = driver.findElement(msgEmployeeAddedLocator);
        return msgEmployeeAdded.isDisplayed() && msgEmployeeAdded.getText().contains("Employee added successfully");
    }
}
