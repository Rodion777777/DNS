package pages;

import com.aplana.DNS.Trash;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage    extends BasePage {

    public void chooseProduct(String name){
        String productXpath = String.format("//*[contains(@href, 'e62623677df11b80') and contains(text(), '%s')]", name);
        WebElement productItem = driver.findElement(By.xpath(productXpath));
        productItem.click();
    }
}
