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

public class testPasAan {
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
    public void test_Alles_Correct_ingevuld(){
        driver.findElement(By.id("PasAanWippetF")).click();
        Clear();
        vulIn(15,350);
        assertEquals("overzicht", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Wippet"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"15"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"350"));


    }

    @Test
    public void test_alles_leeg_ingevuld(){
        driver.findElement(By.id("PasAanWippetF")).click();
        Clear();
        vulIn(0,0);
        assertEquals("change", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Aantal moet positief zijn"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Prijs moet positief zijn"));
    }

    @Test
    public void test_aantal_parameter_leeg_ingevuld(){
        driver.findElement(By.id("PasAanWippetF")).click();
        Clear();
        vulIn(0,250);
        assertEquals("change", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Aantal moet positief zijn"));
    }

    @Test
    public void test_prijs_parameter_leeg_ingevuld(){
        driver.findElement(By.id("PasAanWippetF")).click();
        Clear();
        vulIn(250,0);
        assertEquals("change", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Prijs moet positief zijn"));
    }


    private void vulIn (int inttest1, int inttest2){
        String inttostring1, inttostring2;
        if(inttest1 <= 0 ){
            inttostring1 = "";
        }else {
            inttostring1 = String.valueOf(inttest1);
        }
        if(inttest2 <= 0){
            inttostring2 = "";
        }else {
            inttostring2 = String.valueOf(inttest2);
        }
        driver.findElement(By.id("aantal")).sendKeys(inttostring1);
        driver.findElement(By.id("prijs")).sendKeys(inttostring2);
        driver.findElement(By.id("button")).click();
    }

    private boolean containsWebElementsWithText(List<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    private void Clear(){
        driver.findElement(By.id("aantal")).clear();
        driver.findElement(By.id("prijs")).clear();
    }
}

