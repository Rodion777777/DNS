package pages;

import com.aplana.DNS.Trash;
import com.google.common.base.Function;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class BasketPage extends BasePage {


    @FindBy(xpath = "//div[contains(@class, 'less-wide-screen')]//div[contains(@class, 'radio_checked')]//label[contains(text(), '3 960')]")
    WebElement warranty;
    @FindBy(xpath = "//div[@class='cart-list__products']/div[2]//button[@class='remove-button']")
    WebElement removeGame;
    @FindBy(xpath = "//span[.='21 999']")
    WebElement priceProductElement;
    @FindBy(xpath = "//span[.='1 350']")
    WebElement priceOfGameElement;
    @FindBy(xpath = "//div[@class='total-amount__info-block']//span[.='27 309']")
    WebElement fullPriceElement;
    @FindBy(xpath = "//a[.='Игра  Detroit: Стать человеком (PS4)']")
    public static
    WebElement isGame;
    @FindBy(xpath = "//i[@class='count-buttons__icon-plus']")
    WebElement plusButton;
    @FindBy(xpath = "//span[.='Вернуть удалённый товар']")
    WebElement backButton;

    public boolean checkWarranty(){
        Boolean isPresent = warranty.isEnabled();
        return isPresent;
    }

    public void doRemoveGame() {
        removeGame.click();
    }
    public int pricePSWithoutWarranty(){
       return  Integer.parseInt(Trash.get("playstation").replace(" ", "")) -
               Integer.parseInt(priceProductElement.getText().replace(" ","")) -
               Integer.parseInt(priceOfGameElement.getText().replace(" ","")) +
               Integer.parseInt(fullPriceElement.getText().replace(" ", "")) ;
    }

    public int getPriceOfProductFromSite(){

        return Integer.parseInt(priceProductElement.getText().replace(" ",""));
    }

    public int getPriceOfGame(){
        return Integer.parseInt(priceOfGameElement.getText().replace(" ",""));
    }
    public int getFullPrice(){
        return Integer.parseInt(fullPriceElement.getText().replace(" ",""));
    }
    public static boolean isGameFalse(){
        try {

            if (isGame.isEnabled()) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    public void clickPlusButton(){
            plusButton.click();
    }
    public void clickBackButton(){
        backButton.click();
    }


}
