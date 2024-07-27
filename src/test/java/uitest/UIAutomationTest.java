package uitest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class UIAutomationTest {
    public static void main(String[] args) throws InterruptedException{

        WebDriver driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://www.airalo.com/");

        driver.findElement(By.xpath("//button[text()='ACCEPT']")).click();

        driver.findElement(By.xpath("//button[@class='No thanks']")).click();

        driver.findElement(By.xpath("//span[@data-testid ='close-button']")).click();

        driver.findElement(By.xpath("//input[@data-testid='search-input']")).sendKeys("Japan");

        driver.findElement(By.xpath("//span[text()='Japan']")).click();

        driver.findElement(By.xpath ("(//button[text()='BUY NOW'])[1]")).click();


        String actualOperator=driver.findElement(By.xpath("//div[@data-testid='sim-detail-operator-title']/p")).getText();
        System.out.println(actualOperator);
        Assert.assertEquals(actualOperator,"Moshi Moshi");

        String actualCoverage=driver.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='COVERAGE-value']")).getText();
        System.out.println(actualCoverage);
        Assert.assertEquals(actualCoverage,"Japan");

        String actualData=driver.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='DATA-value']")).getText();
        System.out.println(actualData);
        Assert.assertEquals(actualData,"1 GB");

        String actualValidity=driver.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='VALIDITY-value']")).getText();
        System.out.println(actualValidity);
        Assert.assertEquals(actualValidity,"7 days");

        String actualPrice=driver.findElement(By.xpath("//div[@class='sim-detail-info tw-relative']/ul/li/div/p[@data-testid='PRICE-value']")).getText();
        System.out.println(actualPrice);

        Assert.assertEquals(actualPrice,"$4.50");

    }

}
