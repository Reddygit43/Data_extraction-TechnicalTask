package FileHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileReader {

    public static List<String> datastore;
    public static List<String> datastore2;

    public static void readDataFromFileUsingPattern(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        System.out.println(fileName);
        Pattern pattern=Pattern.compile("[A-Za-z]{2}[0-9]{2}\s*[A-Za-z]{3}");
        datastore = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                datastore.add(matcher.group());

            }
        }

        reader.close();
        System.out.println(datastore);
    }

    public static void readDataFromFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        System.out.println(fileName);

        datastore2 = new ArrayList<String>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {


                datastore2.add(line);

            }
        }catch (IOException e){
            System.out.println(e);
        }

        reader.close();
        System.out.println(datastore);
    }

}



