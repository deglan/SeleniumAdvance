package tests.search;

import driver.DriverSetUp;
import org.junit.jupiter.api.BeforeEach;
import pageObject.home.HomePage;
import pageObject.home.sections.SearchResultsSection;
import utils.search.SearchPageHandler;

public class SearchSetUp extends DriverSetUp {

     SearchPageHandler searchPageHandler;
    HomePage homePage;
    SearchResultsSection searchResultsSection;

     @BeforeEach
    public void setUpSearch() {
         searchPageHandler = new SearchPageHandler(driver);
          homePage = new HomePage(driver);
          searchResultsSection = new SearchResultsSection(driver);
     }
}
