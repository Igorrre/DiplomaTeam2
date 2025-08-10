package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickValue {

    WebDriver driver;
    protected String value;

    public ClickValue(WebDriver driver, String value) {
        this.driver = driver;
        this.value = value;
    }

    public void clickValue() {
        driver.findElement(By.xpath(String.format("//input[@type='radio' and @value='%s']", value))).click();
    }
}