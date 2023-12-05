package utils.checkout;

import configuration.TestContext;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import pageObject.checkout.section.CheckoutAddressFormSection;

@NoArgsConstructor
public class CheckoutAddressFormHandler {

    private CheckoutAddressFormSection addressFormSection;

    public CheckoutAddressFormHandler(WebDriver driver) {
        this.addressFormSection = new CheckoutAddressFormSection(driver);
    }

    public void changeCountry(String country) {
        if ("Poland".equals(country) || "United States".equals(country)) {

            addressFormSection.selectCountry(country);
        } else {
            throw new IllegalArgumentException("Wrong country value: " + country);
        }
    }

    public void fillAndSubmitAddressForm(TestContext testContext, WebDriver driver) {
        CheckoutAddressFormSection form = new CheckoutAddressFormSection.Builder()
                .setCompany(testContext.getProperty("user.company"))
                .setVatNumber(testContext.getProperty("user.vatNumber"))
                .setAddress(testContext.getProperty("user.address"))
                .setAddressComplement(testContext.getProperty("user.addressComplement"))
                .setPostalCode(testContext.getProperty("user.zipCode"))
                .setCity(testContext.getProperty("user.city"))
                .setPhone(testContext.getProperty("user.phone"))
                .build(driver);
        form.clickContinueButton(driver);
    }
}
