package br.com.fps.testforms;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestesElementosBasicos {

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
    public void testTextField(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Francilene");
        String value = driver.findElement(By.id("elementosForm:nome")).getAttribute("value"); // retorna o valor
        assertEquals("Francilene", value);
    }

    @Test
    public void testTextArea(){
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Testando o text Area");
        String value = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"); // retorna o valor
        assertEquals("Testando o text Area", value);
    }

    @Test
    public void testRadioButton(){
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
    }

    @Test
    public void testCheckboxButton(){
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }


    // formas de trabalhar com dropdown
    @Test
    public void testDropdown(){
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdown = new Select(element);

        // selecionando por index
        //dropdown.selectByIndex(2);

        // selecionando pelo valor
        //dropdown.selectByValue("especializacao");

        //Selecionando pelo texto visível
        dropdown.selectByVisibleText("Mestrado");

        assertEquals("Mestrado", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void testVerificacaoDeValoresDoDropdown(){
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select dropdown = new Select(element);

        //verificando os valores disponiveis no dropdown
        List<WebElement> optionsDropdown = dropdown.getOptions();
        assertEquals(8, optionsDropdown.size());

        boolean find = false;
        for(WebElement option: optionsDropdown){
            if (option.getText().equals("Mestrado")){
                System.out.println(option.getText());
                find = true;
                break;
            }

            assertTrue(find);
        }

    }

    @Test
    public void testDropdown2(){
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdown = new Select(element);

        dropdown.selectByVisibleText("Natacao");
        dropdown.selectByVisibleText("Corrida");
        dropdown.selectByVisibleText("O que eh esporte?");

        // Selecionando 3 elementos
        List<WebElement> selectedElements = dropdown.getAllSelectedOptions();
        // verificando se 3 elementos foram selecionados
        assertEquals(3, selectedElements.size());

        // deselecionando uma opção
        dropdown.deselectByVisibleText("O que eh esporte?");
        selectedElements = dropdown.getAllSelectedOptions();
        assertEquals(2, selectedElements.size());
    }

    @Test
    public void testButtonSimples(){
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void testLink(){
        driver.findElement(By.linkText("Voltar")).click();
        WebElement result = driver.findElement(By.id("resultado"));
        assertEquals("Voltou!", result.getText());

        // Assert.fail() - faz o teste falhar
        // ou @Ignore - que ignora o teste

    }

    @Test
    public void testTituloFormComTagName(){
        WebElement tituloForm = driver.findElement(By.tagName("h3"));
        assertEquals("Campo de Treinamento", tituloForm.getText() );

    }

    @Test
    public void testSpanComClassName(){
        WebElement textSpan = driver.findElement(By.className("facilAchar"));
        assertEquals("Cuidado onde clica, muitas armadilhas...", textSpan.getText());
    }



}