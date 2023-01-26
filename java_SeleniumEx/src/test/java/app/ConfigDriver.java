package app;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class ConfigDriver {

    public static WebDriver startBrowserLocal(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "yandex":
                driver = new ChromeDriver(
                        new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("c:\\AQA_Tools\\yandexdriver.exe")).build());
                break;
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe")));
                driver = new FirefoxDriver(options);
                break;
            case "firefox_nightly":
                FirefoxOptions nightlyOptions = new FirefoxOptions();
                nightlyOptions.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
                driver = new FirefoxDriver(nightlyOptions);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver startBrowserRemoteStandalone(String remoteHost) throws MalformedURLException {
        return new RemoteWebDriver(new URL(String.format("http://%s/wd/hub", remoteHost)), new ChromeOptions());
    }

    public static WebDriver startBrowserRemoteHub(String remoteHost, String platform) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platformName", platform);
        return new RemoteWebDriver(new URL(String.format("http://%s/wd/hub", remoteHost)), chromeOptions);
    }

    public static WebDriver startBrowserRemoteCloud(String platform, String user, String accessKey) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platformName", platform);
        return new RemoteWebDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", user, accessKey)), chromeOptions);
    }

    public static void setBrowserSize(WebDriver driver, int width, int height) {
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(width, height));
    }
}
