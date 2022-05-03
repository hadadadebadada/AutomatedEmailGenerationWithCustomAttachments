import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApplicationCreation {
    public static MailSender mailSender = new MailSender();
    public static ArrayList<String> fileList = new ArrayList<>();
    public static ArrayList<String> mailTos = new ArrayList<>();
    public static ArrayList<String> jobsDescs = new ArrayList<>();
    public static ArrayList<String> refNrs = new ArrayList<>();
    public static ArrayList<String> companys = new ArrayList<>();


    public static Paragraph p = new Paragraph("Artur Pfeifer", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLDITALIC, BaseColor.BLACK));
    public static Paragraph anschrift = new Paragraph("Sedanstraße 27");
    public static Paragraph stadt = new Paragraph("49076 Osnabrück");


    public static void main(String[] args) throws IOException, DocumentException, ParseException {



        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/argeinput/new.json"));
        JSONArray jsonArray = (JSONArray) jsonObject.get("stellenangebote");


        String jobDesc = "";
        String company = "";
        String address = "";
        String contactPerson = "Damen und Herren, ";
        String refNr = "";
        String email = "";


        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject stellenangebote = (JSONObject) jsonArray.get(i);

            jobDesc = (String) stellenangebote.get("title");
            jobsDescs.add(jobDesc);
            company = (String) stellenangebote.get("company");
            companys.add(company);
            address = (String) stellenangebote.get("address");
            refNr = (String) stellenangebote.get("url");
            refNrs.add(refNr);
            email = (String) stellenangebote.get("ref");
            mailTos.add(email);


            //create pdf ref
            String fileName = "/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/output/Bewerbung_Ref_Nr_" + refNr + ".pdf";
            fileList.add(fileName);


            Document document = createPdf(fileName);

            addStaticParagraphs(document);
            addDynamicParagraphs(company, contactPerson, address, jobDesc, document);
            addAnschreiben(company, document);
            document.close();

        }

        sendingMails();

    }

    public static void sendingMails(){
        for (int k = 0; k < mailTos.size(); k++) {
            System.out.println("Mail sent to: " + mailTos.get(k));
            mailSender.sendMail(mailTos.get(k), fileList.get(k), jobsDescs.get(k));
        }
    }


    public static Document createPdf(String fileName) throws IOException, DocumentException {
        FileOutputStream fos = new FileOutputStream(fileName);
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, fos);
        document.open();
        PdfContentByte cb = writer.getDirectContent();

// Load existing PDF
        String templateFile = "/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/input/blanknewlast.pdf";
        FileInputStream fis = new FileInputStream(templateFile);
        PdfReader reader = new PdfReader(fis);
        PdfImportedPage page = writer.getImportedPage(reader, 1);

// Copy first page of existing PDF into output PDF
        document.newPage();
        cb.addTemplate(page, 0, 0);

        return document;

    }

    public static void addStaticParagraphs(Document document) throws DocumentException {
        document.add(p);
        document.add(anschrift);
        document.add(stadt);
    }

    public static void addDynamicParagraphs(String company, String contactPerson, String address, String jobDesc, Document document) throws DocumentException {
        Paragraph compPara = new Paragraph(company, new
                Font(Font.FontFamily.HELVETICA, 18, Font.BOLDITALIC, BaseColor.BLACK));
        Paragraph contactPersonPara = new Paragraph(contactPerson);

        Paragraph compCityPlzPara = new Paragraph(address);

        Paragraph jobDescPara = new Paragraph(jobDesc, new
                Font(Font.FontFamily.HELVETICA, 20, Font.UNDERLINE, BaseColor.BLACK));

        document.add(new Paragraph("\n\n"));

        document.add(compPara);

        if (contactPerson == "Damen und Herren, ") {
            //System.out.println("Keinen Kontakt gefunden");
        } else {
            document.add(contactPersonPara);
        }
        document.add(compCityPlzPara);
        document.add(new Paragraph("\n\n"));
        document.add(jobDescPara);
    }

    public static void addAnschreiben(String company, Document document) throws DocumentException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        document.add(new Paragraph("\n\n\n"));
        document.add(new Paragraph("                                                                                                                      Osnabrück, " + dtf.format(now)));
        document.add(new Paragraph("Sehr geehrte Damen und Herren,\n" +
                "\n" +
                "da Sie einen Mitarbeiter mit einem breit gefächerten Tech Stack suchen und dazu ein hochmotiviertes, interdisziplinäres Tech- und Produktteam bieten, hat mich Ihre Stelle direkt angesprochen. Zurzeit schreibe ich meine Bachelorarbeit, welche die Themen Datenarchitekturen, Daten Visualisierung und Data Mining im Studiengang betriebliches Informationsmanagement abdeckt. Ich werde voraussichtlich im Juli mit B.Sc. abschließen. \n" +
                "\n" +
                "Seit August 2021 arbeite ich außerdem als Werkstudent bei der ITEBO Unternehmensgruppe und übernehme dort bereits Verantwortung für eigene Kundenprojekte. Zusätzlich habe ich neben meinen Tätigkeiten im Projektmanagement unterschiedliche Automatisierungswerkzeuge mit Python und Java entwickelt, welche im täglichen Betrieb eingesetzt werden.\n" +
                "\n" +
                "Weitere Code-Beispiele finden Sie auf meiner Portfolio Seite:\n" +
                "www.arturpfeifer.surge.sh\n" +
                "\n" +
                "Wenn ich Ihr Interesse wecken konnte, freue ich mich über die Einladung zu einem\n" +
                "persönlichen Gespräch. Gerne möchte ich Sie davon überzeugen, dass " + company + " mit mir einen leistungsfähigen, kommunikativen und verlässlichen Mitarbeiter gewinnt, welcher motiviert ist alle seine Fähigkeiten mit vollem Einsatz einzubringen, um Ihrem Unternehmen zum Erfolg zu verhelfen.\n" +
                "\n" +
                "Mit freundlichen Grüßen"));



//                document.add(new Paragraph("Sehr geehrte Damen und Herren,\n" +
//                "\n" +
//                "da Sie einen Mitarbeiter mit einem breit gefächerten Verständnis von Daten und deren Zusammenhang zur Realität suchen und dazu ein hochmotiviertes, interdisziplinäres Tech- und Produktteam bieten, hat mich Ihre Stelle direkt angesprochen. Zurzeit schreibe ich meine Bachelorarbeit, welche die Themen Datenarchitekturen, Daten Visualisierung und Data Mining im Studiengang betriebliches Informationsmanagement abdeckt. Ich werde voraussichtlich im Juli mit B.Sc. abschließen \n" +
//                "\n" +
//                "Seit August 2021 arbeite ich außerdem als Werkstudent bei der ITEBO Unternehmensgruppe und übernehme dort bereits Verantwortung für eigene Kundenprojekte. Zusätzlich habe ich neben meinen Tätigkeiten im Projektmanagement unterschiedliche Automatisierungs- und Web Scraping Werkzeuge entwickelt, welche sich im täglichen Einsatz befinden.\n" +
//                "\n" +
//                "Weitere Code-Beispiele finden Sie auf meiner Portfolio Seite:\n" +
//                "www.arturpfeifer.surge.sh\n" +
//                "\n" +
//                "Wenn ich Ihr Interesse wecken konnte, freue ich mich über die Einladung zu einem\n" +
//                "persönlichen Gespräch. Gerne möchte ich Sie davon überzeugen, dass " + company + " mit mir einen leistungsfähigen, kommunikativen und verlässlichen Mitarbeiter gewinnt, welcher motiviert ist alle seine Fähigkeiten mit vollem Einsatz einzubringen, um Ihrem Unternehmen zum Erfolg zu verhelfen.\n" +
//                "\n" +
//                "Mit freundlichen Grüßen"));


    }

}
