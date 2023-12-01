package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.component.user.UserBuilder;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthdate;
    private boolean offersOptIn;
    private boolean customerPrivacy;
    private boolean newsletterOptIn;
    private boolean acceptTerms;

    public User(UserBuilder builder) {
        this.gender = builder.getGender();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.email = builder.getEmail();
        this.password = builder.getPassword();
        this.birthdate = builder.getBirthdate();
        this.offersOptIn = builder.isOffersOptIn();
        this.customerPrivacy = builder.isCustomerPrivacy();
        this.newsletterOptIn = builder.isNewsletterOptIn();
        this.acceptTerms = builder.isAcceptTerms();
    }
}
