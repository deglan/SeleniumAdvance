package base;

import configuration.TestContext;
import lombok.Getter;

@Getter
public enum UrlProvider {

    HOME_URL("urls.homeUrl"),
    REGISTER_URL("urls.registerUrl"),
    LOGIN_URL("urls.loginUrl"),
    LOGGED_USER_URL("urls.loggedUserUrl"),
    FORGOT_PASSWORD_URL("urls.forgotPasswordUrl"),
    BASKET_URL("urls.basketUrl");

    private final String configKey;

    UrlProvider(String configKey) {
        this.configKey = configKey;
    }

    public String getUrl() {
        TestContext testContext = TestContext.getInstance();
        String specificPath = testContext.getProperty(this.configKey);

        if (specificPath.startsWith("http://") || specificPath.startsWith("https://")) {
            return specificPath;
        } else {
            String baseUrl = testContext.getProperty("urls.homeUrl");
            return baseUrl + specificPath;
        }
    }
}