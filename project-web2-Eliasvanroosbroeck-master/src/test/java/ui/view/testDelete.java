package ui.view;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class testDelete {
    WebDriver driver;
    String url = "http://localhost:8080/elias_vanroosbroeck_war_exploded/";
    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"Servlet?command=overzicht");
    }
    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void delete_last_item_returns_overzicht_page(){
        driver.findElement(By.cssSelector("body > main > article > table > tbody:nth-child(2) > tr:nth-child(4) > td:nth-child(6) > a")).click();
        driver.findElement(By.cssSelector("body > main > article > div > form > button:nth-child(4)")).click();
        assertEquals("overzicht", driver.getTitle());
    }

}

