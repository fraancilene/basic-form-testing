package br.com.fps.testforms;

import br.com.fps.dsl.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestesElementosBasicos {

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
        //driver.quit();
    }

    @Test
    public void testTextField(){

        dsl.toWrite("elementosForm:nome","Francilene" );
        assertEquals("Francilene", dsl.getValueField("elementosForm:nome"));
    }

    @Test
    public void testTextArea(){
        dsl.toWrite("elementosForm:sugestoes", "Testando o text Area");
        assertEquals("Testando o text Area", dsl.getValueField("elementosForm:sugestoes"));
    }

    @Test
    public void testRadioButton(){
        dsl.clickButtonRadio("elementosForm:comidaFavorita:0");
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
    }

    @Test
    public void testCheckboxButton(){
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(dsl.checkButtonSelected("elementosForm:sexo:0"));
    }


    // formas de trabalhar com dropdown
    @Test
    public void testDropdown(){
        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Mestrado");
        assertEquals("Mestrado", dsl.getSelectedValueFromDropdown("elementosForm:escolaridade"));
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

        dsl.dropdownSelectedOption("elementosForm:esportes", "Natacao");
        dsl.dropdownSelectedOption("elementosForm:esportes", "Corrida");
        dsl.dropdownSelectedOption("elementosForm:esportes", "O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select dropdown = new Select(element);


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
        dsl.clickButton("buttonSimple");

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        assertEquals("Obrigado!",botao.getAttribute("value"));
    }

    @Test
    public void testLink(){
        dsl.clickLink("Voltar");
        assertEquals("Voltou!", dsl.getText("resultado"));

        // Assert.fail() - faz o teste falhar
        // ou @Ignore - que ignora o teste

    }

    @Test
    public void testTituloFormComTagName(){
        assertEquals("Campo de Treinamento", dsl.getTextBy(By.tagName("h3")));

    }

    @Test
    public void testSpanComClassName(){
        assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getTextBy(By.className("facilAchar")));
    }


    @Test
    public void testJavascript(){

        dsl.runJS("document.getElementById('elementosForm:nome').value = 'Escrito via js'");

        // alterando elemento via js
        dsl.runJS("document.getElementById('elementosForm:sobrenome').type = 'radio'");

        dsl.runJS("arguments[0].style.border = arguments[1]", driver.findElement(By.id("elementosForm:nome")), "solid 4px red");


    }


}