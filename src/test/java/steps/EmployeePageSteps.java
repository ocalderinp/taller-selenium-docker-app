package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.EmployeePage;
import utils.TestContext;

public class EmployeePageSteps {
    private TestContext testContext;
    private EmployeePage employeePage;
    private WebDriver driver;
    private static String expectedTitle = "Add Employee v2 – Verstand QA";

    public EmployeePageSteps(TestContext context){
        this.testContext = context;
        this.driver = this.testContext.getWebDriverManager().getDriver();
        this.employeePage = new EmployeePage(this.driver);
    }

    @Then("^verifico que estoy en la pagina de add employee logueado con el usuario \"([^\"]*)\"$")
    public void verificoQueEstoyEnLaPaginaDeAddEmployeeLogueadoConElUsuario(String user) {
        Assert.assertTrue(employeePage.loggedInAsUser(user),
                "El usuario logueado no es " + user);
    }

    @Then("verifico que estoy en la pagina de annadir empleado")
    public void verificoQueEstoyEnLaPaginaDeAnnadirEmpleado() {
        Assert.assertTrue(employeePage.verifyTitle(expectedTitle),
                "Los titulos no coinciden, se esperaba: " + employeePage.getTitle() + " y se obtuvo: " + this.driver.getTitle());
        Assert.assertTrue(employeePage.atAddEmployeePage(), "No estamos en la pagina de add employee");
    }

    @And("hago logout en la aplicacion")
    public void hagoLogoutEnLaAplicacion() {
        employeePage.logout();
    }

    @Then("^annado empleado con los datos \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
    public void annadoEmpleadoConLosDatosY(String name, String address, String city, String estado, String zipcode, String email, String telefono) {
        employeePage.addEmployee(name, address, city, estado, zipcode, email, telefono);
    }

    @And("^verifico que se adiciono el empleado$")
    public void verificoQueSeAdicionoElEmpleado() {
        Assert.assertTrue(employeePage.isEmployeeAddedSuccesfuly(), "No se adiciono el empleado");
    }
}
