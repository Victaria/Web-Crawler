package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class chrome_crawler {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
        WebDriver cd = new ChromeDriver();
        cd.navigate().to("https://www.google.com/");
        cd.findElement(By.id("lst-ib")).sendKeys("how to be a nice boy?");
        cd.findElement(By.name("btnK")).click();
    }
}
