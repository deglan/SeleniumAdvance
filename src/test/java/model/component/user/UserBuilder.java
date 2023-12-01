package model.component.user;


import lombok.Data;
import model.User;

@Data
public class UserBuilder {

    String gender;
    String firstName;
    String lastName;
    String email;
    String password;
    String birthdate;
    boolean offersOptIn;
    boolean customerPrivacy;
    boolean newsletterOptIn;
    boolean acceptTerms;

    public UserBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public UserBuilder setOffersOptIn(boolean offersOptIn) {
        this.offersOptIn = offersOptIn;
        return this;
    }

    public UserBuilder setCustomerPrivacy(boolean customerPrivacy) {
        this.customerPrivacy = customerPrivacy;
        return this;
    }

    public UserBuilder setNewsletterOptIn(boolean newsletterOptIn) {
        this.newsletterOptIn = newsletterOptIn;
        return this;
    }

    public UserBuilder setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
        return this;
    }

    public User build() {

        return new User(this);
    }
}
