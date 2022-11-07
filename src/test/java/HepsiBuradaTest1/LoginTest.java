package HepsiBuradaTest1;


import java.lang.Thread;
import java.util.Random;

import PageObjectModel.AbstractClass;
import PageObjectModel.Login_page;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends AbstractClass {

    static Login_page _loginPage = new Login_page();
    Random rnd = new Random();

    @Test
    public void test01(){
        _loginPage.goToHepsiburada();

        // Sayfa başarılı bir şekilde yüklendiyse true döner.
        Assert.assertTrue(elementVerify("//div[@id='myAccount']"));
        System.out.println("Going to Hepsiburada.com");
    }

    @Test
    public void test02() throws InterruptedException {

        Thread.sleep(rnd.nextInt(2000,4500));

        _loginPage.loginButtonHover();
        // Buttonun üzerine gelindiğinde giriş linki tıklanabiliyorsa test başarılı..
        Assert.assertTrue(elementVerify("//a[@id='login']"));
        System.out.println("Hover the Login Button");

        Thread.sleep(rnd.nextInt(2000,4500));

        _loginPage.loginButtonClick();
        // Giriş sayfasındaki username inputu gözüküyorsa tıklama başarılı anlamına gelmektedir..
        Assert.assertTrue(elementVerify("//input[@name='username']"));
        System.out.println("Click the Login Button");
    }

    @Test
    public void test03() throws InterruptedException {
        _loginPage.EnterUserName();

        Assert.assertTrue(elementVerify("//input[@name='password']"));
        System.out.println("Entered username and click login button");
    }

    @Test
    public void test04() throws InterruptedException {
        _loginPage.EnterPassword();

        Assert.assertTrue(elementVerify("//a[contains(.,'Çıkış Yap')]"));
        System.out.println("Entered password and click login button");
    }
}
