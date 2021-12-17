package BaseTest;

import MainPage.Dump.GoStartDriver;
import org.jetbrains.annotations.Contract;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static MainPage.Dump.Config.Clear_cookies_and_storage;
import static MainPage.Dump.Config.Hold_Browser_Open;
import static MainPage.Dump.Constant.TimeOutVarriable.Explicitt_Wait;
import static MainPage.Dump.Constant.TimeOutVarriable.SleepInMillis;
import static MainPage.Dump.Constant.Urls.yandexPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseTest {
    public static WebDriver driver = GoStartDriver.createDriver();
    public static WebDriverWait wait;
    JavascriptExecutor searchDriverText = (JavascriptExecutor) driver;


    @Test
    @DisplayName("Яндекс")
    public void goTest () {
        driver.get(yandexPage);
        assertTrue(driver.getPageSource().contains("Яндекс"));//нашли элемент на странице
        if (driver.getPageSource().contains("Авто.ру")) {
            findElement(By.xpath("//*[@data-id='autoru']")).click();
        }
        driver.quit();





    }


//    public WebElement searchText (String text) {
//        try {
//            wait = new WebDriverWait(driver, Explicitt_Wait, SleepInMillis);
//            driver.getPageSource().contains(text);
////            WebElement element =
//            ((JavascriptExecutor)driver).executeScript("arguments[0]['style']['backgroundColor']='darksalmon';", text);
//            return element;
//        } catch (RuntimeException e){
//            throw e;
//        }
//    }
//    public WebElement searchText (String text) {
//        driver.getPageSource().contains(text);
//
//        return null;
//    }

    public WebElement findElement(By xpath) {
        try {
            wait = new WebDriverWait(driver, Explicitt_Wait, SleepInMillis);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
            ((JavascriptExecutor)driver).executeScript("arguments[0]['style']['backgroundColor']='darksalmon';", element);
            return element;
        } catch (RuntimeException e){
            throw e;
        }
    }

    @AfterTest // после тестов закрывает
    public void clearCookiesAndLocalStarage() {
        if(Clear_cookies_and_storage){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear"); // очищаем историю где лазели
        }
    }
    @AfterSuite(alwaysRun = true)// драйвер освобождает ресурсы вконце
    public  void close () {
        if (Hold_Browser_Open) {
            driver.quit(); // освобождает ресурсы (close закрывает текущ вкладку)
        }
    }

}


//    public void searchTest (String username)
//    { //используем когда нужно искать конкретное имя и щелкать что то рядом
//        driver.findElement(By.xpath("//a[contains(.,'" + username
//                + "')]/ancestor::div[contains(@class, 'js-answer-element')]//div[contains(@class,'js-approve-button-text')][contains(.,'Aprobă')]")).click();
//    }
//тоже самое только другой способ
//JavascriptExecutor je = (JavascriptExecutor)driver;
//WebElement element = driver.findElement(By.xpath("//a[contains(text(),'David2211')]//preceding::button[2]/div/div[contains(text(),'Aproba')]"));
//je.executeScript("arguments[0].scrollIntoView(true);",element);
//element.click();
