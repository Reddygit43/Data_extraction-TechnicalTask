package StepDefinitions;

import FileHandling.TextFileReader;
import PageObject.valuationPage;
import Utils.fileComparison;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class FileDataExtract extends BaseTest {

    private String filepath;
    WebDriver driver = setUp();

    public valuationPage vp;
    public fileComparison fp;


    @Given("I have a text file with path {string}")
    public void i_have_a_text_file_with_path(String string) {
        this.filepath = string;
    }

    @When("I read the content of the file using the pattern")
    public void i_read_the_content_of_the_file_using_the_pattern() throws Exception {
        TextFileReader.readDataFromFileUsingPattern(filepath);
    }

    @When("then value each registration at Cazoo website and write data to an external file")
    public void then_value_each_registration_at_cazoo_website() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        for (String vrm : TextFileReader.datastore) {
            vp = new valuationPage(driver);
            driver.navigate().to("https://www.cazoo.co.uk/value-my-car");
            vp.clickOnCookieButton();
            vp.enterRegistration(vrm);
            vp.clickValuationButton(vrm);
        }


    }

    @Then("I compare contents of the file {string}")
    public void i_compare_contents_of_the_file(String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        fp = new fileComparison(driver);
        TextFileReader.readDataFromFile("datastore.txt");
        for (String data : TextFileReader.datastore2) {
            System.out.println(data);
            fp.findStringInTextFile(string, data);

        }
        quitDriver();

    }


}
