package PageObjectModel;

import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class Login_page extends AbstractClass {

    WebDriver webDriver;
    Random random = new Random();

    public Login_page(){
        webDriver = Driver.getWebDriver();
        PageFactory.initElements(webDriver,this);
    }

    public void goToHepsiburada(){
        webDriver.navigate().to("https://www.hepsiburada.com/");
    }

    public void loginButtonHover(){
        while (true){
        Actions actions = new Actions(webDriver);
        WebElement accountButton = webDriver.findElement(By.xpath("//div[@id='myAccount']"));
        actions.moveToElement(accountButton).click().build().perform();
        if (elementVerify("//a[@id='login']")){
            break;
        }
        }
    }

    public void loginButtonClick(){
        Actions actions1 = new Actions(webDriver);
        WebElement loginButton =  webDriver.findElement(By.xpath("//a[@id='login']"));
        actions1.click(loginButton).click().build().perform();
    }

    public void EnterUserName() throws InterruptedException {
        webDriverWait.until(webDriver -> webDriver.findElement(By.xpath("//input[@name='username']")));
        webDriver.findElement(By.xpath("//input[@name='username']")).sendKeys("contactburakmert@gmail.com");
        Thread.sleep(random.nextInt(4000,7000));
        webDriver.findElement(By.xpath("//input[@name='username']")).sendKeys(Keys.ENTER);

    }

    public void EnterPassword() throws InterruptedException {
        webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234");
        Thread.sleep(random.nextInt(4000,7000));
        webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);
    }
}
