import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScrapyLinkCreator{


    public static ArrayList<String> refNrs = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/argeinput/links5.json"));
        JSONArray jsonArray = (JSONArray) jsonObject.get("stellenangebote");


        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject stellenangebote = (JSONObject) jsonArray.get(i);
            String refNr = (String) stellenangebote.get("refnr");
           // System.out.println(refNr);
            refNrs.add(refNr);
        }


        for (int k = 0; k < refNrs.size(); k++) {
            String url = "https://www.arbeitsagentur.de/jobsuche/jobdetail/";
            System.out.println('"' + url + refNrs.get(k) + '"' + ',');
        }
    }

}

