package model.component.user;

import com.github.javafaker.Faker;
import configuration.TestContext;
import model.User;

import java.text.SimpleDateFormat;

public class UserFactory {
    private static final Faker faker = new Faker();
    private static final TestContext testContext = TestContext.getInstance();

    public static User getRandomUser() {
        return new UserBuilder()
                .setGender(faker.options().option("Mr", "Mrs"))
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setBirthdate(new SimpleDateFormat("MM/dd/yyyy").format(faker.date().birthday()))
                .setOffersOptIn(faker.bool().bool())
                .setCustomerPrivacy(faker.bool().bool())
                .setNewsletterOptIn(faker.bool().bool())
                .setAcceptTerms(faker.bool().bool())
                .build();
    }

    public static User getAlreadyRegisteredUser() {
        return new UserBuilder()
                .setGender(testContext.getProperty("user.gender"))
                .setFirstName(testContext.getProperty("user.firstName"))
                .setLastName(testContext.getProperty("user.lastName"))
                .setEmail(testContext.getProperty("user.email"))
                .setPassword(testContext.getProperty("user.password"))
                .setBirthdate(testContext.getProperty("user.birthdate"))
                .setOffersOptIn(testContext.getProperty("user.offersOptIn"))
                .setCustomerPrivacy(testContext.getProperty("user.customerPrivacy"))
                .setNewsletterOptIn(testContext.getProperty("user.newsletterOptIn"))
                .setAcceptTerms(testContext.getProperty("user.acceptTerms"))
                .build();
    }
}
