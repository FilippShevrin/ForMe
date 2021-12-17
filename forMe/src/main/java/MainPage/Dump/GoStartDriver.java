package MainPage.Dump;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GoStartDriver {
    public static WebDriver createDriver(){
        WebDriver driver = null;
        switch (Config.Platform_And_Browser) {
            case ("win_chrome"): {
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwiches", new String[]{"enable-automation"});// убираем надпись что работает машина
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver96.exe");
                driver = new ChromeDriver();
                //определение пути до драйвера и его настройка
                break;
            }
            case ("ubunty_chrome"): {
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwiches", new String[]{"enable-automation"});
                System.setProperty("webdriver.chrome.driver", Config.driverNow);
                driver = new ChromeDriver();
                break;
            }
            default:
//                Assert.fail("Не корректная работа браузера: " + Platform_And_Browser );
//                ошибка не подтягивает org.testing.assert;

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constant.TimeOutVarriable.Implocitt_wait, TimeUnit.SECONDS);

        return driver;
    }
}
