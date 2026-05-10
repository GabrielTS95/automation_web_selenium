package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By username     = By.id("user-name");
    private By password     = By.id("password");
    private By loginBtn     = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public void navigateToLogin() {
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).submit();
    }

    public String validarLogin() {
        return driver.findElement(By.className("app_logo")).getText();
    }

    public String obtenerMensajeError() {
        return driver.findElement(errorMessage).getText();
    }
}
