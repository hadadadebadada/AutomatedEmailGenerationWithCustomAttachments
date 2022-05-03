import com.itextpdf.text.DocumentException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DubChecker {


    public static ArrayList<String> companysOld = new ArrayList<>();
    public static String companyOld = "";

    public static ArrayList<String> companysNew = new ArrayList<>();
    public static String companyNew = "";

    public static ArrayList<String> doubles = new ArrayList<>();
    static JSONParser jsonParser = new JSONParser();

    public static void main(String[] args) throws IOException, ParseException {
        printCompany();
        ArrayList<String> doubles2 = removeDuplicates(doubles);
        System.out.println(doubles2.toString());

    }

    public static JSONArray  returnJSONArray(String filePath) throws IOException, ParseException {

        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
        JSONArray jsonArray = (JSONArray) jsonObject.get("stellenangebote");

        return jsonArray;

    }

    public static void printCompany() throws IOException, ParseException {

        JSONArray jsonArray = returnJSONArray("/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/argeinput/all.json");
        JSONArray jsonArray2 = returnJSONArray("/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/argeinput/new.json");

        for (int c = 0; c < jsonArray2.size(); c++) {
            JSONObject stellenangebote = (JSONObject) jsonArray2.get(c);
            companyNew = (String) stellenangebote.get("company");
            companysNew.add(companyNew);
           // System.out.println(companysNew.get(c));
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject stellenangebote = (JSONObject) jsonArray.get(i);
            companyOld = (String) stellenangebote.get("company");
            companysOld.add(companyOld);
            for (int k = 0; k < companysOld.size(); k++) {
                for (int h = 0; h < companysNew.size(); h++) {
                    if (companysNew.get(h).equals(companysOld.get(k))) {
                        doubles.add(companysNew.get(h));
                    }
                }
            }
        }
    }


    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }


}
