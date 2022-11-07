package PageObjectModel;

import Utilities.Driver;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionEncoder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MainTest_Page extends AbstractClass {
    WebDriver webDriver = Driver.getWebDriver();
    Random random = new Random();
    public MainTest_Page(){
        PageFactory.initElements(webDriver,this);
    }

    public void goToHepsiBurada(){
        webDriver.navigate().to("https://hepsiburada.com");
    }

    public void ProductSearch(String search) throws InterruptedException {
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath("//div[contains(@role,'combobox')]/input")));
        webDriver.findElement(By.xpath("//div[contains(@role,'combobox')]/input")).sendKeys(search);
        Thread.sleep(546);
        webDriver.findElement(By.xpath("//div[contains(@role,'combobox')]/input")).sendKeys(Keys.ENTER);
    }

    //Select product bölümünde arama esnasında websitesi seleniumu algıladığı için aramadan sonra anasayfaya atmakta
    //Bu problemi ürün listesi görünene kadar driverde geri gitme olarak çözüm sağlandı
    public void SelectProduct() throws InterruptedException {
        Thread.sleep(1500);
        WebDriverWait waitt = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        waitt.until(webDriver -> webDriver.findElement(By.xpath("(//ul[contains(@class,'productList')])[1]/li[1]//h3")));
         WebElement element =  webDriver.findElement(By.xpath("(//ul[contains(@class,'productList')])[1]/li[1]//h3"));
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);

    }

    public String GetProductName(){
        for (var x:webDriver.getWindowHandles()) {
            webDriver.switchTo().window(x);
        }

        return webDriver.findElement(By.xpath("//h1[@id='product-name']")).getText();
    }

    public void AddToCartProductFirstShop() throws InterruptedException {
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath("//span[@class='addToCartButton']/button")));
        webDriver.findElement(By.xpath("//span[@class='addToCartButton']/button")).click();
        Thread.sleep(random.nextInt(2500,4500));
    }

    public void ContinueShopping() throws InterruptedException {
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath("//a[contains(@class,'checkoutui-Modal')]")));
        webDriver.findElement(By.xpath("//a[contains(@class,'checkoutui-Modal')]")).click();
        Thread.sleep(random.nextInt(2500,4500));
    }

    public void AddToCartProductSecondShop() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        js.executeScript("window.scrollBy(0,550)");
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath("(//button[contains(.,'Sepete Ekle')])[2]")));
        webDriver.findElement(By.xpath("(//button[contains(.,'Sepete Ekle')])[2]")).click();
        Thread.sleep(random.nextInt(4000,6500));
    }

    public void GoToCart() throws InterruptedException {
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath("//button[@type='button'][contains(.,'Sepete git')]")));
        webDriver.findElement(By.xpath("//button[@type='button'][contains(.,'Sepete git')]")).click();
        Thread.sleep(random.nextInt(2000,3500));
    }

    public boolean VerifyCart(String productName){
        webDriverWait.until(webDriver -> webDriver.findElements(By.xpath("//span[contains(@class,'merchantLink')]/a")));
        String firstShop = webDriver.findElement(By.xpath("(//span[contains(@class,'merchantLink')]/a)[1]")).getAttribute("href");
        String secondShop = webDriver.findElement(By.xpath("(//span[contains(@class,'merchantLink')]/a)[2]")).getAttribute("href");


        var list = webDriver.findElements(By.xpath("(//div[contains(@class,'product_name')])//a"));

        if (firstShop != secondShop){
            for (var product: list) {
                String text = product.getText();
                if (!productName.contains(text)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
