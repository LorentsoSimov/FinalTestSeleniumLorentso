import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterNewCustormer {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
       driver.quit();
    }


    @Test
    public void registerNewCustomer() {
        driver.get("https://shop.pragmatic.bg/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement myAccountIcon = driver.findElement(By.xpath("//*[@class=\"fa fa-user\"]"));
        myAccountIcon.click();
        WebElement registerButton = driver.findElement(By.xpath("//*[@class=\"dropdown-menu dropdown-menu-right\"]/li[1]"));
        registerButton.click();
        driver.findElement(By.id("input-firstname")).sendKeys("Lorentso");
        driver.findElement(By.id("input-lastname")).sendKeys("Simov");
        WebElement emailFieldInput = driver.findElement(By.id("input-email"));
        String prefix = RandomStringUtils.randomAlphanumeric(10);
        String sufix = RandomStringUtils.randomAlphabetic(5);
        String emailAdress = prefix + "@" + sufix + ".com";
        emailFieldInput.sendKeys(emailAdress);
        WebElement telephoneFieldInput = driver.findElement(By.id("input-telephone"));
        String sufixx = RandomStringUtils.randomNumeric(9);
        String telephoneNumber = "+359" + sufixx;
        telephoneFieldInput.sendKeys(telephoneNumber);
        driver.findElement(By.id("input-password")).sendKeys("abvgd12345");
        driver.findElement(By.id("input-confirm")).sendKeys("abvgd12345");
        WebElement privacyPolicyCheckbox = driver.findElement(By.xpath("//*[@name=\"agree\"]"));
        privacyPolicyCheckbox.click();
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"btn btn-primary\"]")));
        continueButton.click();
        WebElement accountCreatedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));
        String accountCreatedText = accountCreatedMessage.getText();
        Assert.assertEquals(accountCreatedText, "Your Account Has Been Created!");


    }

}