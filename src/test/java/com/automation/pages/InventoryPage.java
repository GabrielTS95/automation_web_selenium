package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By appLogo = By.className("app_logo");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerTextoLogo() {
        return getText(appLogo);
    }

    public void abrirMenuLateral() {
        click(menuButton);
        waitUntilVisible(logoutLink);
    }

    public void cerrarSesion() {
        clickWithJavaScript(logoutLink);
    }
}
