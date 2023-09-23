package br.com.fps.testforms;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FramesEjanelas {

    private WebDriver driver;

    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/qa-tester/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 765));
        driver.get("file:" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void testFrames(){
        // mudando o foco para dentro do frame
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();

        Assert.assertEquals("Frame OK!", textAlert);
        alert.accept();

        // tirando o foco do frame
        driver.switchTo().defaultContent();

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(textAlert);

    }

    @Test
    public void testJanelas(){
        driver.findElement(By.id("buttonPopUpEasy")).click();

        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();

        // retornando para a página principal
        driver.switchTo().window("");

        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }

    @Test
    public void testWindowHandler(){
        driver.findElement(By.id("buttonPopUpHard")).click();

        //System.out.println(driver.getWindowHandle());
        //System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");

        driver.close();

        //retornando para a página principal
        driver.switchTo().window("");

        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }





}
