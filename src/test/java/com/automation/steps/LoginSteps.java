package com.automation.steps;

import com.automation.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.DriverFactory;

public class LoginSteps {

    LoginPage login = new LoginPage(DriverFactory.getDriver());

    @Given("el usuario esta en la pagina de login")
    public void elUsuarioEstaEnLaPaginaDeLogin() {
        login.navigateToLogin();
    }

    @When("el cliente ingresa el usuario {string} y la contrasena {string}")
    public void elUsuarioIngresaElUsuarioYLaContrasena(String usuario, String pass) {
        login.enterUsername(usuario);
        login.enterPassword(pass);
    }

    @And("da clic en el boton de login")
    public void daClicEnElBotonDeLogin() {
        login.clickLogin();

    }

    @Then("deberia ver la pagina principal con el texto {string}")
    public void deberiaVerLaPaginaPrincipalConElTexto(String textoEsperado) {
        String actualText =  login.validarLogin();
        Assert.assertTrue("❌ Texto esperado no encontrado. Actual: " + actualText,
                actualText.contains(textoEsperado));
        System.out.println("✔️ Validación exitosa: Se encontró el texto '" + textoEsperado + "'");
    }

    @Then("deberia ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        String mensajeActual = login.obtenerMensajeError();
        Assert.assertTrue("Mensaje de error esperado no encontrado. Actual: " + mensajeActual,
                mensajeActual.contains(mensajeEsperado));
        System.out.println("Validacion exitosa: Se encontro el mensaje de error '" + mensajeEsperado + "'");
    }
}
