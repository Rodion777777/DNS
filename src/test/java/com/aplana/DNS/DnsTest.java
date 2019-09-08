package com.aplana.DNS;
/* +1) открыть dns-shop
+2) в поиске найти playstation
+3) кликнуть по playstation 4 slim black
+4) запомнить цену
5) Доп.гарантия - выбрать 2 года
6) дождаться изменения цены и запомнить цену с гарантией
7) Нажать Купить
8) выполнить поиск Detroit
9) запомнить цену
10) нажать купить
11) проверить что цена корзины стала равна сумме покупок
12) перейри в корзину
13) проверить, что для приставки выбрана гарантия на 2 года
14) проверить цену каждого из товаров и сумму
15) удалить из корзины Detroit
16) проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
18) нажать вернуть удаленный товар, проверить что Detroit появился в корзине и сумма увеличилась на его значение
Подсказки:
Отдельные PageObject - поиск, страница с результатами, карточка товара, корзина, (и, возможно, позиция товара)
Следует создать отдельный класс Product - который будет являться моделью купленного товара (с полями цена, гарантия,
описание и что-тро еще)
Методы должны быть максимально параметризируемые, позволяющие проверить любой товар, и выполнить с ним любые шаги из
данного теста.
* */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasketPage;
import pages.MainPage;
import pages.ProductCard;
import pages.ResultsPage;
import static junit.framework.TestCase.assertEquals;
import java.util.concurrent.TimeUnit;

public class DnsTest {
    WebDriver driver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TestProperties.getInstance().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        Trash.driver = driver;

    }


    @Test
    public void checkPS() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.sendMessage("playstation");
        WebDriverWait wait = new WebDriverWait(driver, 6, 1000);
        ResultsPage resultsPage = new ResultsPage();
        resultsPage.chooseProduct("PlayStation 4 Slim Black 1 TB");
        ProductCard productCard = new ProductCard();
        productCard.savePriceOfCurrentProduct("playstation");
        productCard.selectWarranty1("2 года");
        productCard.savePriceOfCurrentProduct("playstation");
        productCard.buyButtonClick();
        mainPage.sendMessage("Detroit\n");
        productCard.savePriceOfCurrentProduct("game");
        productCard.buyButtonClick();
        wait.until(mainPage.valueChanged);
        assertEquals("цена корзины не  равна сумме покупок", Trash.summa(), mainPage.getTotalPrice());
        mainPage.goToBasket();
        BasketPage basketPage = new BasketPage();
        assertEquals("для приставки не выбрана гарантия на 2 года", true, basketPage.checkWarranty());
        assertEquals("цена приставки не совпадает ", 21999, basketPage.getPriceOfProductFromSite());
        assertEquals("цена игры не совпадает", Integer.parseInt(Trash.get("game")), basketPage.getPriceOfGame());
        assertEquals("цена корзины не  равна сумме покупок", Trash.summa(), basketPage.getFullPrice());
        basketPage.doRemoveGame();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[.='Игра  Detroit: Стать человеком (PS4)']"), 0));
        assertEquals("Detroit всё ещё в корзине", true, basketPage.isGameFalse());
        assertEquals("сумма уменьшилась на цену Detroit", Integer.parseInt(Trash.get("game")), Trash.summa()-Integer.parseInt(Trash.get("playstation")));
        basketPage.clickPlusButton();
        wait.until(mainPage.valueChanged2PS);
        basketPage.clickPlusButton();
        wait.until(mainPage.valueChanged3PS);
        assertEquals("сумма не равная трем PS", Integer.parseInt(Trash.get("playstation"))*3, mainPage.getTotalPrice());
        basketPage.clickBackButton();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[.='Игра  Detroit: Стать человеком (PS4)']"), 1));
        wait.until(mainPage.valueChanged3PSAndGame);
        assertEquals("Detroit нет в корзине", false, basketPage.isGameFalse());
        assertEquals("сумма не увеличиласть на стоимость Detroit", Integer.parseInt(Trash.get("playstation"))*3+Integer.parseInt(Trash.get("game")), mainPage.getTotalPrice());
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}

