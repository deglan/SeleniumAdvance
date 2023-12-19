package step.checkout;

import configuration.TestContext;
import org.openqa.selenium.WebDriver;
import pageObject.checkout.section.CheckoutAddressFormSection;

public class CheckoutAddressFormStep extends CheckoutAddressFormSection {

    private CheckoutAddressFormSection addressFormSection;

    public CheckoutAddressFormStep(WebDriver driver) {
        super(driver);
    }


    public void changeCountry(String country) {
        addressFormSection = new CheckoutAddressFormStep(driver);
        if ("Poland".equals(country) || "United States".equals(country)) {
            addressFormSection.selectCountry(country);
        } else {
            throw new IllegalArgumentException("Wrong country value: " + country);
        }
    }

    public void fillAndSubmitAddressForm(TestContext testContext, WebDriver driver) {
        CheckoutAddressFormSection form = new CheckoutAddressFormSection(driver);
        form.setCompany(testContext.getProperty("user.company"))
                .setVatNumber(testContext.getProperty("user.vatNumber"))
                .setAddress(testContext.getProperty("user.address"))
                .setAddressComplement(testContext.getProperty("user.addressComplement"))
                .setPostalCode(testContext.getProperty("user.zipCode"))
                .setCity(testContext.getProperty("user.city"))
                .setPhone(testContext.getProperty("user.phone"));
        form.clickContinueButton(driver);
    }
}
