package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

public class valuationPage {

    public WebDriver driver;
    private final String filePath = "datastore.txt";
    private final BufferedWriter writer;

    @FindBy(css = "button[class='sc-mqi3p7-0 gihvmi'] span")
    private WebElement acceptCookieButton;

    @FindBy(css = "#vrm")
    private WebElement enterVRM;

    @FindBy(xpath = "//form[@action='/sell/']//button[@type='submit']")
    private WebElement valuationButton;

    @FindBy(xpath = "//main[@id='main-content']//div//div//div//h1")
    private WebElement resultPage;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alert;

    @FindBy(xpath = "//div[@data-test-id='your-registration-number-summary']//p[2]")
    private WebElement vehicleDetails;

    public valuationPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        try {
            writer = new BufferedWriter(new FileWriter("datastore.txt",true));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickOnCookieButton(){
        try{
            acceptCookieButton.isDisplayed();
            acceptCookieButton.click();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void enterRegistration(String vrm){
        enterVRM.sendKeys(vrm);

    }

    public void clickValuationButton(String vrm) throws IOException {
        valuationButton.click();
        resultPage.isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOf(alert));

            String message = alert.getText();
            Scanner scanner = new Scanner(message);
            StringBuilder sb = getStringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line);
            }
            String newMessage = vrm + ", " + sb;


            writer.write(newMessage);
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            vehicleDetails = wait.until(ExpectedConditions.visibilityOf(vehicleDetails));
            String vehicleDetailsMessage = vehicleDetails.getText();
            String newMessage=vehicleDetailsMessage.replace("Make/model:", "").stripLeading();

            Scanner scanner = new Scanner(vehicleDetailsMessage);
            StringBuilder sb = getStringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line);

            }

            String Message = vrm + "," + newMessage;



            writer.write(Message);

        }
        writer.newLine();
        writer.flush();

    }


    private static StringBuilder getStringBuilder() {
        StringBuilder sb = new StringBuilder();
        return sb;
    }



}
