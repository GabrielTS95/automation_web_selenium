package com.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.automation.steps", "com.automation.hooks"},
    plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = true
)
public class TestRunner {

    @AfterClass
    public static void generarInformeDeAllure() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                processBuilder.command("cmd.exe", "/c", "allure generate --single-file ./target/allure-results -o ./target/Reporte_Allure --clean");
            } else {
                processBuilder.command("bash", "-c", "allure generate --single-file ./target/allure-results -o ./target/Reporte_Allure --clean");
            }

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Logger.getLogger(TestRunner.class.getName()).log(Level.INFO, line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                Logger.getLogger(TestRunner.class.getName()).log(Level.INFO, "Reporte Allure generado exitosamente");
            } else {
                Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, "Error al generar reporte Allure. Codigo de error: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, "Error en el comando Allure", e);
        }
    }
}
