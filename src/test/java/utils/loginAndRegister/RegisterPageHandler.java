package utils.loginAndRegister;

import model.User;
import model.component.user.UserFactory;
import org.openqa.selenium.WebDriver;
import pageObject.loginAndRegister.RegisterPage;


public class RegisterPageHandler {

    public void fillUpRegistrationCorrect(WebDriver driver) {
        User randomUser = UserFactory.getRandomUser();
        RegisterPage registerPage = new RegisterPage(driver);
        fillRegistrationForm(registerPage, randomUser);
        registerPage.submitForm();
    }

    public void fillUpRegistrationWithRegisteredUser(WebDriver driver) {
        User registeredUser = UserFactory.getAlreadyRegisteredUser();
        RegisterPage registerPage = new RegisterPage(driver);
        fillRegistrationForm(registerPage, registeredUser);
        registerPage.submitForm();
    }

    private void fillRegistrationForm(RegisterPage registerPage, User user) {
        registerPage.selectGender(user.getGender());
        registerPage.enterFirstName(user.getFirstName());
        registerPage.enterLastName(user.getLastName());
        registerPage.enterEmail(user.getEmail());
        registerPage.enterPassword(user.getPassword());
        registerPage.enterBirthdate(user.getBirthdate());
        registerPage.setOffersOptIn(Boolean.parseBoolean(String.valueOf(user.isOffersOptIn())));
        registerPage.setCustomerPrivacy(true);
        registerPage.setNewsletterOptIn(Boolean.parseBoolean(String.valueOf(user.isNewsletterOptIn())));
        registerPage.setAcceptTerms(true);
    }
}
