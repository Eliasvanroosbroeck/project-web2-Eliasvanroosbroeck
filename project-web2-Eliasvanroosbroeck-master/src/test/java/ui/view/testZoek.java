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

public class testZoek {
    WebDriver driver;
    String url = "http://localhost:8080/elias_vanroosbroeck_war_exploded/";

    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"Servlet?command=zoek");
    }
    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void Search_for_found_object(){
        vulIn("Wippet","Female");
        assertEquals("zoekResultaat", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Naam: Wippet"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Soort: F"));
    }

    @Test
    public void Search_for_non_existant_object(){
        vulIn("Wippet","Male");
        assertEquals("zoekResultaat", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("p")), "We hebben het ras met naam Wippet voor je opgezocht."));

    }

    @Test
    public void no_search_without_given_name_returns_error(){
        vulIn("","Female");
        assertEquals("zoek", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Het ras mag niet leeg zijn"));
    }

    private void vulIn(String test1,String test2){
        driver.findElement(By.id("naam")).sendKeys(test1);
        driver.findElement(By.id("geslacht")).sendKeys(test2);
        driver.findElement(By.cssSelector("body > main > article > div.gridform1 > form > p:nth-child(3) > input")).click();
    }

    private boolean containsWebElementsWithText(List<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

}
