package com.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class Hooks {

//    private DriverFactory driverFactory = new DriverFactory();

//    @Before
//    public void setUp() {
//        System.out.println("SE ESTA INICIANDO EL ESCENARIO");
//        DriverFactory.getDriver().manage().window().maximize();
//    }


    @After
    public void tearDown(){
        DriverFactory.closeDriver();
    }


    //Metodo para capturar la pantalla después de cada escenario
    @AfterStep
    public void afterEachStep(Scenario scenario) {
        try{
            byte[] screenshot = DriverFactory.takeScreenshot();
            if (screenshot.length > 0) {
                Allure.addAttachment("Step Screenshot - " + scenario.getName(), new ByteArrayInputStream(screenshot));
                System.out.println("ESTE ES EL NOMBRE"+scenario.getName());
            }
        } catch (RuntimeException e) {
            System.out.println("No se logra capturar:");
        }
    }


}
