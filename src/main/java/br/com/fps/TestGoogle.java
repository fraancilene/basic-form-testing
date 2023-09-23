package br.com.fps;

import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {

    @Test
    public void teste() {
        //System.setProperty("webdriver.gecko.driver", "/Users/francilenesilva/documents/qa-tester/drivers/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/qa-tester/drivers/chromedriver");

        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

        // definindo a posição e tamanho da janela
        driver.manage().window().setSize(new Dimension(1000, 765));
        //driver.manage().window().maximize(); // para a tela maximizada


        driver.get("http://www.alura.com.br");
        Assert.assertEquals("Alura | Cursos online de Tecnologia", driver.getTitle());
        driver.quit(); // fecha todas as abas e fecha a instancia
        //driver.close(); // apenas fecha a aba
    }
}
