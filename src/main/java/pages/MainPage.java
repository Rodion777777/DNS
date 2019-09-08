package pages;

import com.aplana.DNS.Trash;
import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{

    @FindBy(xpath = "//div[@class='buttons']//span[@class='btn-cart-link__price']//*[contains(@data-of, 'totalPrice')]")
    static
    WebElement totalPrice;

    @FindBy(xpath = "//input[@class='ui-input-search__input main-search-form__input ui-autocomplete-input']")
    WebElement searchTextField;


    @FindBy(xpath = "//nav[@id='header-search']//span[@class='ui-input-search__icon ui-input-search__icon_search']")
    WebElement searchButton;


    public void sendMessage(String text){

        searchTextField.sendKeys(text);
        searchButton.click();
    }
    public static int getTotalPrice(){
        int price = 0;
        String som = totalPrice.getText().replace(" ", "");
        if (!som.equals("Корзина")){
         price = Integer.parseInt(som);}
        else price = 0;
        return price;
    }
    public void goToBasket(){
        totalPrice.click();
    }
    public Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {

        @Override
        public Boolean apply(WebDriver webDriver) {
            int summa1 = Trash.summa();
            int summa2 = MainPage.getTotalPrice();
            return  (summa1==summa2);
        }
    };

    public Function<? super WebDriver, Object> valueChanged2PS = new ExpectedCondition<Object>() {

        @Override
        public Boolean apply(WebDriver webDriver) {
            int summa3 = Integer.parseInt(Trash.get("playstation"))*2;
            int summa4 = MainPage.getTotalPrice();
            return  (summa3==summa4);
        }
    };
    public Function<? super WebDriver, Object> valueChanged3PS = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                int summa5 = Integer.parseInt(Trash.get("playstation")) * 3;
                int summa6 = MainPage.getTotalPrice();
                return (summa5 == summa6);
            }
        };
    public Function<? super WebDriver, Object> valueChanged3PSAndGame = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            int summa5 = Integer.parseInt(Trash.get("playstation")) * 3 + Integer.parseInt(Trash.get("game"));
            int summa6 = MainPage.getTotalPrice();
            return (summa5 == summa6);
        }
    };


}
