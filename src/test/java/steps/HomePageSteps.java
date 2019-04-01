package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import utils.TestContext;

public class HomePageSteps {
    private TestContext testContext;
    private HomePage homePage;
    private WebDriver driver;
    private static String expectedTitle = "Login Employee v2 – Verstand QA";

    public HomePageSteps(TestContext context){
        this.testContext = context;
        this.driver = this.testContext.getWebDriverManager().getDriver();
        this.homePage = new HomePage(this.driver);
    }

    @Then("^navego a la pagina de inicio$")
    public void navegarALogin() {
        homePage.navegarAInicio();
    }

    @When("^intento loguearme en la aplicacion con usuario \"([^\"]*)\" password \"([^\"]*)\"$")
    public void intentoLoguearmeEnLaAplicacionConUsuarioPassword(String user, String password) {
        homePage.loginAs(user, password);
    }

    @And("verifico que estoy en la pagina de inicio")
    public void verificoQueEstoyEnLaPaginaDeInicio() {
        Assert.assertEquals(expectedTitle, homePage.getTitle(),
                "No estoy en la pagina de inicio");
    }

    @Then("verifico que no puedo loguearme en la aplicacion")
    public void verificoQueNoPuedoLoguearmeEnLaAplicacion() {
        Assert.assertTrue(homePage.msgBadCredentilasDisplayed(),
                "No se muestra el mensaje de credenciales invalidas");
    }

    @When("^intento loguearme en la aplicacion con datos validos$")
    public void intentoLoguearmeEnLaAplicacionConDatosValidos() {
        homePage.login();
    }
}
