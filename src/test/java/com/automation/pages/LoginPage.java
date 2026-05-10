package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class LoginPage extends BasePage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLogin() {
        navigateTo(ConfigReader.get("base.url", "https://www.saucedemo.com/"));
    }

    public void enterUsername(String user) {
        type(username, user);
    }

    public void enterPassword(String pass) {
        type(password, pass);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public String obtenerMensajeError() {
        return getText(errorMessage);
    }

    public boolean estaEnPaginaLogin() {
        return isVisible(loginBtn);
    }
}
