package pageObject.home;

import org.openqa.selenium.WebDriver;
import pageObject.category.ProductCategoryPage;
import pageObject.home.sections.FooterSection;
import pageObject.home.sections.HeaderSection;
import pageObject.home.sections.PopularProductListSection;
import pageObject.home.sections.SearchWidgetSection;

public class HomePage {

    private WebDriver driver;
    private HeaderSection headerSection;
    private SearchWidgetSection searchWidgetSection;
    private PopularProductListSection productListSection;
    private FooterSection footerSection;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.headerSection = new HeaderSection(driver);
        this.searchWidgetSection = new SearchWidgetSection(driver);
        this.productListSection = new PopularProductListSection(driver);
        this.footerSection = new FooterSection(driver);
    }

    public PopularProductListSection getProductListSection() {
        return new PopularProductListSection(driver);
    }

    public SearchWidgetSection getSearchWidgetSection() {
        return new SearchWidgetSection(driver);
    }

    public ProductCategoryPage getCategoryPage() {
        return new ProductCategoryPage(driver);
    }
}
