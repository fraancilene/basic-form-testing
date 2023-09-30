package br.com.fps.dsl;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver){
        this.driver = driver;
    }

    // REFRESH HOMEPAGE
    public void refreshHomePage(){
        driver.navigate().refresh();
    }

    // TEXTFIELD AND TEXTAREA
    public void toWriteBy(By by, String text){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);

    }
    public void toWrite(String fieldId, String text){
        driver.findElement(By.id(fieldId)).sendKeys(text);

    }
    public String getValueField(String fieldId){
      return driver.findElement(By.id(fieldId)).getAttribute("value");
    }

    // RADIO AND CHECKBOX
    public void clickButtonRadio(String fieldId){
        driver.findElement(By.id(fieldId)).click();
    }

    public void clickButtonCheckbox(String fieldId){
        driver.findElement(By.id(fieldId)).click();
    }

    public boolean checkButtonSelected(String fieldId){
        return driver.findElement(By.id(fieldId)).isSelected();
    }

    // DROPDOWNS

    public void dropdownSelectedOption(String fieldId, String value){
        WebElement element = driver.findElement(By.id(fieldId));
        Select dropdown = new Select(element);

        // selecionando por index
        //dropdown.selectByIndex(2);

        // selecionando pelo valor
        //dropdown.selectByValue("especializacao");

        //Selecionando pelo texto visível
        dropdown.selectByVisibleText(value);
    }

    public String getSelectedValueFromDropdown(String fieldId){
        WebElement element = driver.findElement(By.id(fieldId));
        Select dropdown = new Select(element);
        return dropdown.getFirstSelectedOption().getText();

    }

    public void dropdownDeselectOption(String fieldId, String value){
        WebElement element = driver.findElement(By.id(fieldId));
        Select dropdown = new Select(element);
         dropdown.deselectByVisibleText(value);
    }

    public List<String> getOptionsDropdown(String fieldId){
        WebElement element = driver.findElement(By.id(fieldId));
        Select dropdown = new Select(element);
        List<WebElement> allSelectedOptions = dropdown.getAllSelectedOptions();
        List<String> values = new ArrayList<String>();
        for (WebElement option: allSelectedOptions){
            values.add(option.getText());
        }

        return values;
    }

    public int getQuantityOptionsDropdown(String fieldId){
        WebElement element = driver.findElement(By.id(fieldId));
        Select dropdown = new Select(element);
        List<WebElement> options = dropdown.getOptions();
        return options.size();
    }

    public boolean verifyOptionPresentInDropdown(String fieldId, String presentOption){
        WebElement element = driver.findElement(By.id(fieldId));
        Select dropdown = new Select(element);
        List<WebElement> options = dropdown.getOptions();
        for (WebElement option: options){
            if (option.getText().equals(presentOption)){
                return true;
            }
        }
        return false;
    }


    // BUTTONS
    public void clickButton(String fieldId){
        driver.findElement(By.id(fieldId)).click();
    }

    // LINKS
    public void clickLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    // GET TEXTS
    public String getTextBy(By by){
        return driver.findElement(by).getText();
    }
    public String getText(String fieldId){
       return getTextBy(By.id(fieldId)); // está usando o método de cima
    }

    // ALERTS
    public String getTextAlert(){
        Alert alert = driver.switchTo().alert();
       return alert.getText();
    }

    public String getTextAlertAndAccept(){
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();
        return textAlert;
    }


    public String getTextAlertAndDismiss(){
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.dismiss();
        return textAlert;
    }


    public void writeInAlertAndAccept(String textAlert){
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys(textAlert);
        prompt.accept();
    }

    public void getTextAndAcceptPrompt(String textAlert){
        Alert prompt = driver.switchTo().alert();
        prompt.getText();
        prompt.accept();
    }

    public void getTextAndDismissPrompt(String textAlert){
        Alert prompt = driver.switchTo().alert();
        prompt.getText();
        prompt.dismiss();
    }

    // FRAMES
    public void openFrame(String nameFrame){
        driver.switchTo().frame(nameFrame);
    }


    public void returnHomePage() {
        driver.switchTo().defaultContent();
    }
    public void returnHomePageWithId(String idHome) {
        driver.switchTo().defaultContent();
    }

    public void changeWindow(String idWindow){
        driver.switchTo().window(idWindow);
    }

    public void closeFrame(){
        driver.close();
    }
}





