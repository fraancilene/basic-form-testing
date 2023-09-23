package br.com.fps.testforms;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestesAlerts {

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
    public void testAlertSimples(){
        driver.findElement(By.id("alert")).click();

        // alterando o foco para o alerta
        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Alert Simples", textoAlerta);
        alert.accept(); // aceitando o alerta

        driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);
    }

    @Test
    public void testConfirmandoAlerta(){
        driver.findElement(By.id("confirm")).click();

        // alterando o foco para o alerta
        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Confirm Simples", textoAlerta);
        alert.accept(); // aceitando o alerta

        String textoAlerta2 = alert.getText();
        assertEquals("Confirmado", textoAlerta2);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta2);

    }

    @Test
    public void testNegandoAlerta(){
        driver.findElement(By.id("confirm")).click();

        // alterando o foco para o alerta
        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Confirm Simples", textoAlerta);
        alert.dismiss(); // aceitando o alerta

        String textoAlerta2 = alert.getText();
        assertEquals("Negado", textoAlerta2);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta2);

    }

    @Test
    public void testPrompt(){
        driver.findElement(By.id("prompt")).click();

        Alert prompt = driver.switchTo().alert();
        assertEquals("Digite um numero", prompt.getText());
        prompt.sendKeys("12");
        prompt.accept();

        assertEquals("Era 12?", prompt.getText());
        prompt.accept();

        assertEquals(":D", prompt.getText());
        prompt.accept();

    }
}
