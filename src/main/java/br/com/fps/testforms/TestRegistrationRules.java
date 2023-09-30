package br.com.fps.testforms;

import br.com.fps.dsl.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestRegistrationRules {

    private WebDriver driver;
    private DSL dsl;
    private RegistrationPage page;

    @Parameterized.Parameter(value = 0)
    public String nome;

    @Parameterized.Parameter(value = 1)
    public String sobrenome;

    @Parameterized.Parameter(value = 2)
    public String gender;

    @Parameterized.Parameter(value = 3)
    public List<String> foods;

    @Parameterized.Parameter(value = 4)
    public String[] sports;

    @Parameterized.Parameter(value = 5)
    public String msg;



    @Before
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/qa-tester/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 765));
        driver.get("file:" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page  = new RegistrationPage(driver);
    }

    @After
    public void quitBrowser(){
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
            {"", "Silva", "male", Arrays.asList(""), new String[]{}, "Nome eh obrigatorio"},
            {"Francilene", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
            {"Francilene", "Silva", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
            {"Francilene", "Silva", "female", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
            {"Francilene", "Silva", "female", Arrays.asList("Carne"), new String[]{"Futebol", "O que eh esporte?" }, "Voce faz esporte ou nao?"}

        });
    }


    @Test
    public void validateRules(){

        page.setName(nome);
        page.setSurname(sobrenome);

        if(gender.equals("male")){
            page.setMaleGender();
        }
        if(gender.equals("female")){
            page.setFemaleGender();
        }

        if (foods.contains("Carne")) page.setFavoriteFoodMeat();
        if (foods.contains("Frango")) page.setFavoriteFoodChicken();
        if (foods.contains("Pizza")) page.setFavoriteFoodPizza();
        if (foods.contains("Vegetariano")) page.setFavoriteFoodVegan();


        //page.setEducation("Superior");

        page.setSport(sports);
        page.registration();
        System.out.println(msg);

        assertEquals(msg, page.validateAlert());
    }
}
