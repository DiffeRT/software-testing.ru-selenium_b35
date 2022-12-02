import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.File;

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
}
