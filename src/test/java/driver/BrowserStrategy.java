package driver;

import browsers.BrowserDriver;
import browsers.EdgeBrowserDriver;
import browsers.FirefoxBrowserDriver;
import browsers.IEBrowserDriver;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import browsers.ChromeBrowserDriver;

public enum BrowserStrategy {

    CHROME(new ChromeBrowserDriver()),
    FIREFOX(new FirefoxBrowserDriver()),
    IE(new IEBrowserDriver()),
    EDGE(new EdgeBrowserDriver());

    private final BrowserDriver browserDriver;

    BrowserStrategy(BrowserDriver browserDriver) {
        this.browserDriver = browserDriver;
    }

    public WebDriver getDriver() {
        return browserDriver.createDriver();
    }

    private static final Map<String, BrowserStrategy> BY_NAME = Arrays.asList(values()).stream()
            .collect(Collectors.toMap(value -> value.browserDriver.toString(), Function.identity()));

    public static BrowserStrategy from(String name) {
        return BY_NAME.getOrDefault(name.toUpperCase(), CHROME);
    }
}
