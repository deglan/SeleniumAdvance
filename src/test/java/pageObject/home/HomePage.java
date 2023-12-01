package pageObject.home;

import org.openqa.selenium.WebDriver;
import pageObject.category.ProductCategoryPage;
import pageObject.home.sections.FooterSection;
import pageObject.home.sections.HeaderSection;
import pageObject.home.sections.ProductListSection;
import pageObject.home.sections.SearchWidgetSection;

public class HomePage {

    private WebDriver driver;
    private HeaderSection headerSection;
    private SearchWidgetSection searchWidgetSection;
    private ProductListSection productListSection;
    private FooterSection footerSection;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.headerSection = new HeaderSection(driver);
        this.searchWidgetSection = new SearchWidgetSection(driver);
        this.productListSection = new ProductListSection(driver);
        this.footerSection = new FooterSection(driver);
    }

    public ProductListSection getProductListSection() {
        return new ProductListSection(driver);
    }

    public SearchWidgetSection getSearchWidgetSection() {
        return new SearchWidgetSection(driver);
    }

    public ProductCategoryPage getCategoryPage() {
        return new ProductCategoryPage(driver);
    }
}
