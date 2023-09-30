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
    private DSL dsl;

    @Before
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/qa-tester/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 765));
        driver.get("file:" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void quitBrowser(){
        driver.quit();
    }


    @Test
    public void testCadastroComErroDeNomeObrigatorio(){
        dsl.toWrite("elementosForm:nome", "");
        dsl.toWrite("elementosForm:sobrenome", "Silva");
        dsl.clickButtonRadio("elementosForm:sexo:1");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");

        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Superior");
        dsl.dropdownSelectedOption("elementosForm:esportes", "Futebol" );
        dsl.clickButton("elementosForm:cadastrar");
        dsl.getTextAlertAndAccept();


        assertEquals("Status: Nao cadastrado", dsl.getText("resultado"));

    }

    @Test
    public void testCadastroComErroDeSobrenomeObrigatorio(){

        dsl.toWrite("elementosForm:nome", "Pedro");
        dsl.toWrite("elementosForm:sobrenome", "");
        dsl.clickButtonRadio("elementosForm:sexo:0");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");

        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Superior");

        dsl.dropdownSelectedOption("elementosForm:esportes", "Futebol" );

        dsl.clickButton("elementosForm:cadastrar");

        dsl.getTextAlertAndAccept();

        assertEquals("Status: Nao cadastrado", dsl.getText("resultado"));
    }

    @Test
    public void testCadastroComErroDeSexoObrigatorio(){

        dsl.toWrite("elementosForm:nome", "Francilene");
        dsl.toWrite("elementosForm:sobrenome", "Silva");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");
        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Superior");
        dsl.dropdownSelectedOption("elementosForm:esportes", "Futebol" );
        dsl.clickButton("elementosForm:cadastrar");

        dsl.getTextAlertAndAccept();

        assertEquals("Status: Nao cadastrado", dsl.getText("resultado"));
    }

    @Test
    public void testCadastroComErroOqueEhEsporte(){
        dsl.toWrite("elementosForm:nome", "Francilene");
        dsl.toWrite("elementosForm:sobrenome", "Silva");
        dsl.clickButtonRadio("elementosForm:sexo:1");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");

        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Superior");

        dsl.dropdownSelectedOption("elementosForm:esportes", "Futebol" );
        dsl.dropdownSelectedOption("elementosForm:esportes", "O que eh esporte?" );

        dsl.clickButton("elementosForm:cadastrar");

        dsl.getTextAlertAndAccept();


        assertEquals("Status: Nao cadastrado", dsl.getText("resultado"));


    }


    @Test
    public void testCadastroComErroOqueEhVegetarianoOuNao(){

        dsl.toWrite("elementosForm:nome", "Francilene");
        dsl.toWrite("elementosForm:sobrenome", "Silva");
        dsl.clickButtonRadio("elementosForm:sexo:1");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:3");

        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Superior");
        dsl.dropdownSelectedOption("elementosForm:esportes", "Futebol" );
        dsl.clickButton("elementosForm:cadastrar");


        dsl.getTextAlertAndAccept();

        assertEquals("Status: Nao cadastrado", dsl.getText("resultado"));


    }


    @Test
    public void cadastroComSucesso(){

        dsl.toWrite("elementosForm:nome", "Francilene");
        dsl.toWrite("elementosForm:sobrenome", "Silva");
        dsl.clickButtonRadio("elementosForm:sexo:1");
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");

        dsl.dropdownSelectedOption("elementosForm:escolaridade", "Superior");
        dsl.dropdownSelectedOption("elementosForm:esportes", "Futebol" );
        dsl.clickButton("elementosForm:cadastrar");


        assertEquals("Cadastrado!", dsl.getTextBy(By.xpath("//*[@id=\"resultado\"]/span")));

    }

    @Test
    public void validateWriteenNames(){

        dsl.toWrite("elementosForm:nome", "Francilene");
        assertEquals("Francilene", dsl.getValueField("elementosForm:nome"));

        dsl.toWriteBy(By.id("elementosForm:nome"), "Pedro");
        assertEquals("Pedro", dsl.getValueField("elementosForm:nome"));

    }


}