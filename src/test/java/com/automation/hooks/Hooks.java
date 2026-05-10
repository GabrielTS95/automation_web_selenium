package com.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class Hooks {

    @After
    public void tearDown() {
        DriverFactory.closeDriver();
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        try {
            byte[] screenshot = DriverFactory.takeScreenshot();
            if (screenshot.length > 0) {
                Allure.addAttachment("Step Screenshot - " + scenario.getName(), new ByteArrayInputStream(screenshot));
                System.out.println("Screenshot capturado para: " + scenario.getName());
            }
        } catch (RuntimeException e) {
            System.out.println("No se logro capturar screenshot: " + e.getMessage());
        }
    }
}
