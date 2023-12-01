package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEBrowserDriver implements BrowserDriver{

    @Override
    public WebDriver createDriver() {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }
}
