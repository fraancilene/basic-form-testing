package br.com.fps.testforms;

import br.com.fps.dsl.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestCadastroForms {

    private WebDriver driver;
    private RegistrationPage page;

    @Before
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/qa-tester/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 765));
        driver.get("file:" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page  = new RegistrationPage(driver);
    }

    @After
    public void quitBrowser(){
        driver.quit();
    }

    @Test
    public void cadastroComSucesso(){

        page.setName("Francilene");
        page.setSurname("Silva");
        page.setFemaleGender();
        page.setFavoriteFoodMeat();
        page.setEducation("Superior");
        page.setSport("Futebol");
        page.registration();

        assertEquals("Cadastrado!", page.getRegistrationResult());

    }


    @Test
    public void validateWriteenNames(){
        page.setName("Francilene");
        assertEquals("Francilene", page.getValue());
        page.setNameBy("Pedro");
        assertEquals("Pedro", page.getValue());

    }


}