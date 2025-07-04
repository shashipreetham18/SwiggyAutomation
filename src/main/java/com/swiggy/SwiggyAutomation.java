package com.swiggy;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class SwiggyAutomation {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C://Users//satya//Desktop//chromedriver-win64//chromedriver.exe/");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        
        driver.get("https://www.swiggy.com/");
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());

        
        driver.findElement(By.xpath("//div[text()='Login']")).click();
        driver.findElement(By.id("mobile")).sendKeys("YOUR_PHONE_NUMBER");
        System.out.println("Waiting 30 seconds for OTP entry...");
        Thread.sleep(30000); 

        
        WebElement locBox = driver.findElement(By.id("location"));
        locBox.sendKeys("Bengaluru");
        Thread.sleep(2000);
        locBox.sendKeys(Keys.ARROW_DOWN);
        locBox.sendKeys(Keys.ENTER);
        Thread.sleep(4000);

        
        driver.findElement(By.xpath("//span[text()='Search']")).click();
        Thread.sleep(2000);
        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search for restaurants and food']"));
        searchInput.sendKeys("Domino's Pizza");
        Thread.sleep(2000);
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(4000);

        
        List<WebElement> restaurants = driver.findElements(By.cssSelector("._3XX_A"));
        String restaurantName = restaurants.get(0).getText();
        System.out.println("Selected Restaurant: " + restaurantName);
        restaurants.get(0).click();
        Thread.sleep(4000);

        
        List<WebElement> items = driver.findElements(By.cssSelector("._2wg_t"));
        String foodItemName = items.get(1).getText();
        System.out.println("Selected Food Item: " + foodItemName);
        driver.findElements(By.xpath("//div[text()='Add']")).get(1).click();
        Thread.sleep(2000);
        takeScreenshot(driver, "01_item_added.png");

        
        driver.findElement(By.xpath("//span[text()='View Cart']")).click();
        Thread.sleep(3000);

        
        driver.findElement(By.xpath("//div[@class='_1ds9T']")).click(); // +
        Thread.sleep(2000);
        takeScreenshot(driver, "02_quantity_increased.png");

        
        driver.findElement(By.xpath("//span[text()='Add new address']")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("house")).sendKeys("12A, Prestige Heights");
        driver.findElement(By.name("landmark")).sendKeys("Near MG Road");
        driver.findElement(By.xpath("//button[text()='Home']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Save Address & Proceed')]")).click();
        Thread.sleep(3000);
        takeScreenshot(driver, "03_address_added.png");
        driver.findElement(By.xpath("//button[contains(text(),'Proceed to Pay')]")).click();
        Thread.sleep(3000);
        takeScreenshot(driver, "04_payment_page.png");

        WebElement total = driver.findElement(By.className("_1dqrv"));
        System.out.println("Cart Total: " + total.getText());

        driver.quit();
    }

    public static void takeScreenshot(WebDriver driver, String fileName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("screenshots/" + fileName));
    }
}
