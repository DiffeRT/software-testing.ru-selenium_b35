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
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    public static WebDriver startBrowser(String browser) {
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

    public static WebDriver startRemoteStandaloneDriver() throws MalformedURLException {
        WebDriver driver;
        driver = new RemoteWebDriver(new URL("http://192.168.248.131:4444/wd/hub"), new ChromeOptions());
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1440,900));
        return driver;
    }
}
