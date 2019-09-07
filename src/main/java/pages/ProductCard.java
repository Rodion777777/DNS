package pages;

import com.aplana.DNS.Trash;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductCard extends BasePage {
    @FindBy(xpath = "//*[contains(@class,'hidden-xs ') ]//*[@class='current-price-value']")
            WebElement productPrice;

    @FindBy(xpath = ".//a[@href='/cart']")
            WebElement goToBasket;
    @FindBy(xpath = "//select[@class='form-control select']")
            WebElement selectWarranty;
    @FindBy(xpath = "//button[@class='btn btn-cart btn-lg']")
            WebElement buyButton;


    public void buyButtonClick(){
        buyButton.click();
    }
    public void savePriceOfCurrentProduct(String key){
        String value = productPrice.getText().replace(" ", "");
        Trash.put(key, value);
    }



    public void selectWarranty1(String text){
        Select select = new Select(selectWarranty);
        select.selectByVisibleText(text);
    }


}
