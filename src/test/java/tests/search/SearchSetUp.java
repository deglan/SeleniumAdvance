package tests.search;

import driver.DriverSetUp;
import org.junit.jupiter.api.BeforeEach;
import pageObject.home.HomePage;
import pageObject.home.sections.SearchResultsSection;
import step.search.SearchPageStep;

public class SearchSetUp extends DriverSetUp {

    SearchPageStep searchPageStep;
    HomePage homePage;
    SearchResultsSection searchResultsSection;

    @BeforeEach
    public void setUpSearch() {
        searchPageStep = new SearchPageStep(driver);
        homePage = new HomePage(driver);
        searchResultsSection = new SearchResultsSection(driver);
    }
}
