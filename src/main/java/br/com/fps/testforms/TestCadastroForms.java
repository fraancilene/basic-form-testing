package br.com.fps.testforms;

import com.sun.javafx.image.BytePixelGetter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sun.lwawt.macosx.CPrinterDevice;

import static org.junit.Assert.assertEquals;

public class TestCadastroForms {

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
    public void testCadastroComErroDeNomeObrigatorio(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownComidas = new Select(element);
        dropdownComidas.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownEsportes = new Select(element);
        dropdownEsportes.selectByIndex(2);

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Nome eh obrigatorio", textoAlerta);
        alert.accept();

        String resultado = driver.findElement(By.id("resultado")).getText();
        assertEquals("Status: Nao cadastrado", resultado);

    }

    @Test
    public void testCadastroComErroDeSobrenomeObrigatorio(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Pedro");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownComidas = new Select(element);
        dropdownComidas.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownEsportes = new Select(element);
        dropdownEsportes.selectByIndex(2);

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Sobrenome eh obrigatorio", textoAlerta);
        alert.accept();

        String resultado = driver.findElement(By.id("resultado")).getText();
        assertEquals("Status: Nao cadastrado", resultado);
    }

    @Test
    public void testCadastroComErroDeSexoObrigatorio(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Francilene");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownComidas = new Select(element);
        dropdownComidas.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownEsportes = new Select(element);
        dropdownEsportes.selectByIndex(2);

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Sexo eh obrigatorio", textoAlerta);
        alert.accept();

        String resultado = driver.findElement(By.id("resultado")).getText();
        assertEquals("Status: Nao cadastrado", resultado);
    }

    @Test
    public void testCadastroComErroOqueEhEsporte(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Francilene");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownComidas = new Select(element);
        dropdownComidas.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownEsportes = new Select(element2);
        dropdownEsportes.selectByVisibleText("Futebol");
        dropdownEsportes.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Voce faz esporte ou nao?", textoAlerta);
        alert.accept();

        String resultado = driver.findElement(By.id("resultado")).getText();
        assertEquals("Status: Nao cadastrado", resultado);


    }


    @Test
    public void testCadastroComErroOqueEhVegetarianoOuNao(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Francilene");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:sexo:1")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownComidas = new Select(element);
        dropdownComidas.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownEsportes = new Select(element2);
        dropdownEsportes.selectByVisibleText("Futebol");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Tem certeza que voce eh vegetariano?", textoAlerta);
        alert.accept();

        String resultado = driver.findElement(By.id("resultado")).getText();
        assertEquals("Status: Nao cadastrado", resultado);


    }


    @Test
    public void cadastroComSucesso(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Francilene");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdownComidas = new Select(element);
        dropdownComidas.selectByVisibleText("Superior");

        WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdownEsportes = new Select(element);
        dropdownEsportes.selectByIndex(2);

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        String nomeCadastrado = driver.findElement(By.xpath("//*[@id=\"resultado\"]/span")).getText();
        assertEquals("Cadastrado!", nomeCadastrado);

    }


}