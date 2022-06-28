import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import com.typesafe.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import amazon.pageObjects.TvPageFactory;


public class TestSamsungTv {

    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    private WebDriver driver = DriverFactory.getDriver();
    private TvPageFactory tvPage;

    @Test
    void testSamsungTvDisplay() {
        driver.get(HOME_PAGE_URL);
        tvPage = new TvPageFactory(driver);
        tvPage.clickHamburgerMenu();
        Assertions.assertTrue(tvPage.isMenuDisplayed());
        tvPage.clickMenuListItem("TV, Appliances, Electronics");
        tvPage.clickMenuListItem("Televisions");
        tvPage.scrollIntoTvList();
        Assertions.assertTrue(tvPage.isTvListDisplayed());
        tvPage.clickSamsungTV();
        Assertions.assertTrue(tvPage.isSamsungCheckBoxDisplayed());
        Assertions.assertTrue(tvPage.isSortByFilterDisplayed());
        tvPage.clickSortByFilter();
        tvPage.clickSecondHighestItem();
        Assertions.assertTrue(tvPage.isAboutThisItemTitleDisplayed());
        Assertions.assertTrue(tvPage.isAboutThisItemContentDisplayed());
        driver.quit();
    }

}
