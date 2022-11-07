package PageObjectModel;

import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

public abstract class AbstractClass {

    WebDriver webDriver = Driver.getWebDriver();
    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    private void getElementText(String xpath){
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath(xpath)));

    }

    public boolean elementVerify(String xpath){
        try {
            webDriverWait.until(webDriver -> webDriver.findElement(By.xpath(xpath)));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean elementToBeClickable(String xpath){
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath(xpath))));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public void webDriverRefresh(){
        webDriver.navigate().refresh();
    }

    public void driverClick(String xpath){
        webDriver.findElement(By.xpath(xpath)).click();
    }
}
