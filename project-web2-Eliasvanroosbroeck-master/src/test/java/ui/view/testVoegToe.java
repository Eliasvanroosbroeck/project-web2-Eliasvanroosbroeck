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

public class testVoegToe {
    WebDriver driver;
    String url = "http://localhost:8080/elias_vanroosbroeck_war_exploded/";
    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"Servlet?command=voegToe");
    }
    @After
    public void clean(){
        driver.quit();
    }
    @Test
    public void Form_Shown_again_all_blanks_or_numberic_values_are_lower_than_1(){
        voegToe("",0,"",0);
        assertEquals("voegToe", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Het ras mag niet leeg zijn"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Aantal moet positief zijn"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("li")), "Prijs moet positief zijn"));
    }

    @Test
    public void adding_animal_correctly() {
        voegToe("Pyncher",13,"M",750);
        assertEquals("overzicht", driver.getTitle());
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"Pyncher"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"13"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"M"));
        assertTrue(containsWebElementsWithText(driver.findElements(By.tagName("td")),"750"));
    }

    private void voegToe(String test1, int inttest1, String test2, int inttest2){
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
        driver.findElement(By.id("dier")).sendKeys(test1);
        driver.findElement(By.id("aantal")).sendKeys(inttostring1);
        driver.findElement(By.id("geslacht")).sendKeys(test2);
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

}
