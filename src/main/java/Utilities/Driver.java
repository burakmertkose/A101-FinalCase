package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {

    public static WebDriver webDriver;

    public static WebDriver getWebDriver(){
        if (webDriver == null){
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-blink-features");
            chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
            chromeOptions.setExperimentalOption("useAutomationExtension",false);


            webDriver = new ChromeDriver(chromeOptions);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            webDriver.manage().window().maximize();
            return  webDriver;
        }
        return webDriver;
    }

    public static int chromeHandelCount(){
        int count = webDriver.getWindowHandles().size();
        return count;
    }

}
