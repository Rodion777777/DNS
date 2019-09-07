package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{

    @FindBy(xpath = "//div[@class='buttons']//span[@class='btn-cart-link__price']//*[contains(@data-of, 'totalPrice')]")
    WebElement totalPrice;

    @FindBy(xpath = "//input[@class='ui-input-search__input main-search-form__input ui-autocomplete-input']")
    WebElement searchTextField;


    @FindBy(xpath = "//nav[@id='header-search']//span[@class='ui-input-search__icon ui-input-search__icon_search']")
    WebElement searchButton;


    public void sendMessage(String text){

        searchTextField.sendKeys(text);
        searchButton.click();
    }
    public int getTotalPrice(){
        int price = Integer.parseInt(totalPrice.getText().replace(" ", ""));
        return price;
    }
    public void goToBasket(){
        totalPrice.click();
    }

}
