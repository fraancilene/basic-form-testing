package br.com.fps.testforms;

import br.com.fps.dsl.DSL;
import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FramesEjanelas {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/qa-tester/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 765));
        driver.get("file:" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void testFrames(){
        // mudando o foco para dentro do frame
        dsl.openFrame("frame1");
        dsl.clickButton("frameButton");

        String msg = dsl.getTextAlertAndAccept();
        assertEquals("Frame OK!", msg);
        dsl.returnHomePage();

        //driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(textAlert);

    }

    @Test
    @Ignore
    public void testJanelas(){
        dsl.clickButton("buttonPopUpEasy");
        dsl.changeWindow("Popup");

        dsl.toWriteBy(By.tagName("textarea"), "Deu certo?");
        dsl.closeFrame();

        dsl.returnHomePageWithId("");
        dsl.toWrite("elementosForm:sugestoes", "E agora?");
    }

    @Test
    public void testWindowHandler(){
        dsl.clickButton("buttonPopUpHard");

        //System.out.println(driver.getWindowHandle());
        //System.out.println(driver.getWindowHandles());
        dsl.changeWindow((String) driver.getWindowHandles().toArray()[1]);
        dsl.toWriteBy(By.tagName("textarea"), "Deu certo?");

        dsl.closeFrame();
        dsl.returnHomePageWithId("");

        dsl.toWrite("elementosForm:sugestoes", "E agora?");
    }





}
