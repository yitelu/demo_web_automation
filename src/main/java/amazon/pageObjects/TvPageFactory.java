package amazon.pageObjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class TvPageFactory {

    WebDriver driver;


    //Getting all the elements/selectors on the pages
    @FindBy(css = "#nav-hamburger-menu")
    WebElement hamburgerMenu;
    @FindBy(className = "hmenu-item")
    List<WebElement> menuList;
    @FindBy(css = "#s-refinements > div:nth-child(5) > ul > li:nth-child(4) > span > a > span")
    WebElement menuOptionItemOne;
    @FindBy(css = "#s-refinements > div:nth-child(11) > ul > li:nth-child(4) > span > a > span")
    WebElement menuOptionItemTwo;
    @FindBy(xpath = "//*[@class='a-list-item']/a")
    List<WebElement> tvMenuList;
    @FindBy(css = ".a-section h2 > a > span.a-text-normal")
    List<WebElement> samsungCheckbox;
    @FindBy(css = "h2 > a")
    List<WebElement> item;
    @FindBy(css = "#s-result-sort-select")
    WebElement sortByFilter;
    @FindBy(css = "#feature-bullets > h1")
    WebElement aboutThisItemTitle;
    @FindBy(css = "#feature-bullets > ul > li > span")
    List<WebElement> aboutThisItemList;


    public TvPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //Check that the menu list is displayed after the menu button is clicked.
    public boolean isMenuDisplayed() {
        boolean mainMenu = false;
        try {
            for (WebElement i : menuList) {
                if (i.getText().equalsIgnoreCase("TV, Appliances, Electronics")) {
                    mainMenu = i.isDisplayed();
                    break;
                }
            }
            return mainMenu;
        } catch (Exception e) {
            System.out.print("The side menu list is not displayed \n" + e.getMessage());
            return false;
        }
    }

    //Check that the television list is displayed
    public boolean isTvListDisplayed() {
        boolean tvMenu = false;
        try {
            for (WebElement i : tvMenuList) {
                if (i.getText().equalsIgnoreCase("Samsung")) {
                    tvMenu = i.isDisplayed();
                    break;
                }
            }
            return tvMenu;
        } catch (Exception e) {
            System.out.print("The television list is not displayed \n" + e.getMessage());
            return false;
        }
    }

    //Check that the filter button is displayed
    public boolean isSortByFilterDisplayed() {
        try {
            return sortByFilter.isDisplayed();
        } catch (Exception e) {
            System.out.print("The filter button is not displayed \n" + e.getMessage());
            return false;
        }
    }

    //Check that the name cards of items are displayed to help us confirm each name contains Samsung
    public boolean isSamsungCheckBoxDisplayed() {
        boolean productName = false;
        try {
            for (WebElement i : samsungCheckbox) {
                if (i.getText().contains("Samsung")) {
                    productName = true;
                } else {
                    productName = false; //return false if an element does not contain Samsung
                    break;
                }
            }
            return productName;
        } catch (Exception e) {
            System.out.print("The Samsung is not displayed \n" + e.getMessage());
            return false;
        }
    }

    //Check that about item title is displayed
    public boolean isAboutThisItemTitleDisplayed() {
        try {
            System.out.println("Checked 'About this item” section is present and log this section text to console");
            System.out.println(aboutThisItemTitle.getText());
            return aboutThisItemTitle.isDisplayed();
        } catch (Exception e) {
            System.out.print("The about item header is not displayed \n" + e.getMessage());
            return false;
        }
    }

    //Check that the list of bullet points under about item is displayed
    public boolean isAboutThisItemContentDisplayed() {
        boolean listDescription = false;
        try {
            for (WebElement i : aboutThisItemList) {
                if (i.isDisplayed()) {
                    listDescription = true;
                    System.out.println("- " + i.getText());
                } else {
                    listDescription = false; //return false if list is not displayed
                    break;
                }
            }
            return listDescription;
        } catch (Exception e) {
            System.out.print("The list description is not displayed \n" + e.getMessage());
            return false;
        }
    }


    //Click on the main menu button
    public void clickHamburgerMenu() {
        hamburgerMenu.click();
    }

    //Loop through the main menu list and click on the element that contains the text passed
    //To click on items on the main menu list
    public void clickMenuListItem(String tvText) {
        try {
            for (WebElement i : menuList) {
                if (i.getText().equalsIgnoreCase(tvText)) {
                    i.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("The menu element is not displayed \n" + e.getMessage());
        }
    }

    // scroll through menu list to locate the tv menu list
    public void scrollIntoTvList() {
        try {
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView (true)", menuOptionItemOne);
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView (true)", menuOptionItemTwo);
        } catch (Exception e) {
            System.out.print("unable to select element to scroll in menu list \n" + e.getMessage());
        }
    }

    //Check the tv menu list and click on Samsung
    public void clickSamsungTV() {
        try {
            for (WebElement i : tvMenuList) {
                if (i.getText().equalsIgnoreCase("Samsung")) {
                    i.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("Samsung is not displayed \n" + e.getMessage());
        }
    }

    //Click on the filter sort by price high to low
    public void clickSortByFilter() {
        Select filter = new Select(this.sortByFilter);
        filter.selectByValue("price-desc-rank");
    }

    //Click on the second-highest item.
    public TvPageFactory clickSecondHighestItem() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('target')", item.get(1));
            item.get(1).click();
        } catch (Exception e) {
            System.out.print("The second item is not enabled \n" + e.getMessage());
        }
        return new TvPageFactory(driver);
    }

}
