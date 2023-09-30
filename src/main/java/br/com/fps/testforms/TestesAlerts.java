package br.com.fps.testforms;

import br.com.fps.dsl.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestesAlerts {

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
    public void testAlertSimples(){
        dsl.clickButton("alert");

        String msg = dsl.getTextAlertAndAccept();
        assertEquals("Alert Simples", msg);
        //dsl.getTextAlertAndAccept("Alert Simples");

    }

    @Test
    public void testConfirmandoAlerta(){

        dsl.clickButton("confirm");
        String msg = dsl.getTextAlertAndAccept();
        assertEquals("Confirm Simples", msg);

        //dsl.getTextAlertAndAccept("Confirm Simples");
        String msgAlert2 = dsl.getTextAlertAndAccept();
        assertEquals("Confirmado", msgAlert2);
        dsl.toWrite("elementosForm:sugestoes", msgAlert2);


    }

    @Test
    public void testNegandoAlerta(){
        dsl.clickButton("confirm");

        String msg = dsl.getTextAlertAndDismiss();
        assertEquals("Confirm Simples", msg);

        String msgAlert2 = dsl.getTextAlertAndAccept();
        assertEquals("Negado", msgAlert2);
        dsl.toWrite("elementosForm:sugestoes", msgAlert2);




    }

    @Test
    public void testPromptWithAccept(){

        dsl.clickButton("prompt");
        dsl.writeInAlertAndAccept("12");

        dsl.getTextAndAcceptPrompt("Era 12?");
        dsl.getTextAndAcceptPrompt(":D");

    }

    @Test
    public void testPromptWithDismiss(){

        dsl.clickButton("prompt");
        dsl.writeInAlertAndAccept("12");

        dsl.getTextAndDismissPrompt("Era 12?");
        dsl.getTextAndAcceptPrompt(":(");

    }
}
