package com.automation.steps;

import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Before
    public void setUpPages() {
        WebDriver driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Given("el usuario esta en la pagina de login")
    public void elUsuarioEstaEnLaPaginaDeLogin() {
        loginPage.navigateToLogin();
    }

    @When("el cliente ingresa el usuario {string} y la contrasena {string}")
    public void elUsuarioIngresaElUsuarioYLaContrasena(String usuario, String pass) {
        loginPage.enterUsername(usuario);
        loginPage.enterPassword(pass);
    }

    @And("da clic en el boton de login")
    public void daClicEnElBotonDeLogin() {
        loginPage.clickLogin();
    }

    @And("abre el menu lateral")
    public void abreElMenuLateral() {
        inventoryPage.abrirMenuLateral();
    }

    @And("da clic en logout")
    public void daClicEnLogout() {
        inventoryPage.cerrarSesion();
    }

    @Then("deberia ver la pagina principal con el texto {string}")
    public void deberiaVerLaPaginaPrincipalConElTexto(String textoEsperado) {
        String textoActual = inventoryPage.obtenerTextoLogo();
        Assert.assertTrue("Texto esperado no encontrado. Actual: " + textoActual,
                textoActual.contains(textoEsperado));
        System.out.println("Validacion exitosa: Se encontro el texto '" + textoEsperado + "'");
    }

    @Then("deberia ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        String mensajeActual = loginPage.obtenerMensajeError();
        Assert.assertTrue("Mensaje de error esperado no encontrado. Actual: " + mensajeActual,
                mensajeActual.contains(mensajeEsperado));
        System.out.println("Validacion exitosa: Se encontro el mensaje de error '" + mensajeEsperado + "'");
    }

    @Then("deberia volver a la pagina de login")
    public void deberiaVolverALaPaginaDeLogin() {
        Assert.assertTrue("No se mostro la pagina de login", loginPage.estaEnPaginaLogin());
        System.out.println("Validacion exitosa: El usuario volvio a la pagina de login");
    }
}
