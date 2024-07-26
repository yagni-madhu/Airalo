package uiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class UIAutomationTest {
    public static void main(String[] args) throws InterruptedException{

        WebDriver d  = new ChromeDriver();
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        d.get("https://www.airalo.com/");

        d.findElement(By.xpath("//button[text()='ACCEPT']")).click();

        d.findElement(By.xpath("//button[@class='No thanks']")).click();

        d.findElement(By.xpath("//span[@data-testid ='close-button']")).click();

        d.findElement(By.xpath("//input[@data-testid='search-input']")).sendKeys("Japan");

        d.findElement(By.xpath("//span[text()='Japan']")).click();

        d.findElement(By.xpath ("(//button[text()='BUY NOW'])[1]")).click();


        String actualOperator=d.findElement(By.xpath("//div[@data-testid='sim-detail-operator-title']/p")).getText();
        System.out.println(actualOperator);
        Assert.assertEquals(actualOperator,"Moshi Moshi");

        String actualCoverage=d.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='COVERAGE-value']")).getText();
        System.out.println(actualCoverage);
        Assert.assertEquals(actualCoverage,"Japan");

        String actualData=d.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='DATA-value']")).getText();
        System.out.println(actualData);
        Assert.assertEquals(actualData,"1 GB");

        String actualValidity=d.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='VALIDITY-value']")).getText();
        System.out.println(actualValidity);
        Assert.assertEquals(actualValidity,"7 days");

        String actualPrice=d.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='PRICE-value']")).getText();
        System.out.println(actualPrice);

        Assert.assertEquals(actualPrice,"$4.50");

    }

}
