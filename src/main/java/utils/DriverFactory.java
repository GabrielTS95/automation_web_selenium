package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            driver = createDriver();
            DRIVER.set(driver);
        }
        return driver;
    }

    public static WebDriver getCurrentDriver() {
        return DRIVER.get();
    }

    public static void closeDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    public static byte[] takeScreenshot() {
        WebDriver driver = getCurrentDriver();
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    public static WebDriver initializeDriver() {
        closeDriver();
        return getDriver();
    }

    private static WebDriver createDriver() {
        String browser = ConfigReader.get("browser", "chrome").toLowerCase();
        if ("chrome".equals(browser)) {
            return new ChromeDriver(buildChromeOptions());
        }
        throw new IllegalArgumentException("Navegador no soportado: " + browser);
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
                || ConfigReader.getBoolean("headless", false);
    }
}
