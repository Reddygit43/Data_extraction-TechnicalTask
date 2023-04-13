package Utils;

import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class fileComparison {

    public fileComparison(WebDriver driver) {
    }

    public void findStringInTextFile(String filePath, String data) {
        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(data)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (found) {
            System.out.println("String Found in file");
        } else {
            System.out.println("String not found");
        }
    }

}
