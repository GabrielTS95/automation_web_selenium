package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        try {
            closeDriver();
            driver = new ChromeDriver(buildChromeOptions());
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo iniciar ChromeDriver", e);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static byte[] takeScreenshot() {
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    public static WebDriver initializeDriver() {
        driver = new ChromeDriver(buildChromeOptions());
        return driver;
    }

    private static ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");

        if (isHeadlessExecution()) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
        }

        return options;
    }

    private static boolean isHeadlessExecution() {
        return "true".equalsIgnoreCase(System.getenv("HEADLESS"))
                || "true".equalsIgnoreCase(System.getenv("CI"))
                || "true".equalsIgnoreCase(System.getProperty("headless"));
    }
}
