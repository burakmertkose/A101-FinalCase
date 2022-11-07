package HepsiBuradaTest1;

import PageObjectModel.AbstractClass;
import PageObjectModel.MainTest_Page;
import Utilities.Driver;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest extends AbstractClass {

    static MainTest_Page mainTest_page = new MainTest_Page();
    private static String prodcutName;

    @BeforeClass
    public static void goToHepsiburada(){
        mainTest_page.goToHepsiBurada();
        System.out.println("Go To Hepsiburada.com");
    }

    @Test //Search Product
    public void test01() throws InterruptedException {
        mainTest_page.ProductSearch("Peluş Oyuncak");

        Assert.assertTrue(elementVerify("(//ul[contains(@class,'productList')])[1]/li[1]"));
        System.out.println("Search Product");
    }

    @Test //Select Product
    public void test02() throws InterruptedException {
        mainTest_page.SelectProduct();

        // Ürüne tıklandığında yeni bir chrome sekmesi açılacak
        // Toplamda 2 chrome sekmesi varsa ürün başarıyla açılmış demektir.
        Assert.assertEquals(Driver.chromeHandelCount() , 2);
        System.out.println("Select Product");
    }

    @Test //Get Product Title
    public void test03(){
        prodcutName = mainTest_page.GetProductName();
        System.out.println("Get Product Title");
    }

    @Test //Add To Cart Product First Shop
    public void test04() throws InterruptedException {
        mainTest_page.AddToCartProductFirstShop();

        Assert.assertTrue(elementVerify("//a[contains(@class,'checkoutui-Modal')]"));
        System.out.println("Add To Cart Product First Shop");
    }

    @Test //Click the continue shopping button
    public void test05() throws InterruptedException {
        mainTest_page.ContinueShopping();
        System.out.println("Click the continue shopping button");
    }

    @Test //Add To Cart Product Second Shop
    public void test06() throws InterruptedException {
        mainTest_page.AddToCartProductSecondShop();

        Assert.assertTrue(elementVerify("//button[@type='button'][contains(.,'Sepete git')]"));
        System.out.println("Add To Cart Product Second Shop");
    }

    @Test // Go to Cart
    public void test07() throws InterruptedException {
        mainTest_page.GoToCart();

        Assert.assertEquals("https://checkout.hepsiburada.com/",Driver.webDriver.getCurrentUrl());
        System.out.println("Go to Cart");
    }

    @AfterClass // Verify
    public static void VerifyCart(){
        Assert.assertTrue(mainTest_page.VerifyCart(prodcutName));
        System.out.println("Test Successful");
    }
}
