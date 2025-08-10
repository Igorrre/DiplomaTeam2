package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WriteText {

    WebDriver driver;
    protected String id;

    public WriteText(WebDriver driver, String id) {
        this.driver = driver;
        this.id = id;
    }

    public void writeText(String text) {
        driver.findElement(By.xpath(String.format("//input[@id='%s']", id))).sendKeys(text);
    }
}