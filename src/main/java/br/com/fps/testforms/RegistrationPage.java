package br.com.fps.testforms;

import br.com.fps.dsl.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationPage {
    private DSL dsl;

    public RegistrationPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setName(String name) {
        dsl.toWrite("elementosForm:nome", name);
    }

    public void setNameBy(String name) {
        dsl.toWriteBy(By.id("elementosForm:nome"), name);
    }

    public void setSurname(String surname) {
        dsl.toWrite("elementosForm:sobrenome", surname);
    }

    public void setMaleGender() {
        dsl.clickButtonRadio("elementosForm:sexo:0");
    }

    public void setFemaleGender() {
        dsl.clickButtonRadio("elementosForm:sexo:1");
    }

    public void setFavoriteFoodMeat() {
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:0");
    }

    public void setFavoriteFoodChicken() {
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:1");
    }

    public void setFavoriteFoodPizza() {
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:2");
    }

    public void setFavoriteFoodVegan() {
        dsl.clickButtonCheckbox("elementosForm:comidaFavorita:3");
    }
    public void setEducation(String value) {
        dsl.dropdownSelectedOption("elementosForm:escolaridade", value);
    }

    public void setSport(String... values) {
        for(String value: values){
            dsl.dropdownSelectedOption("elementosForm:esportes", value);
        }

    }

    public void registration() {
        dsl.clickButton("elementosForm:cadastrar");
    }

    public String getRegistrationResult() {
        return dsl.getTextBy(By.xpath("//*[@id=\"resultado\"]/span"));
    }

    public String getRegistrationResultWithId() {
        return dsl.getText("resultado");
    }


    public String getTextAlertAndAccept() {
        return dsl.getTextAlertAndAccept();
    }

    public Object validateAlert(){
        return dsl.getTextAlertAndAccept();
    }

    public String getValue(){
        return dsl.getValueField("elementosForm:nome");
    }
}

