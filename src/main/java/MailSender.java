
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailSender {


    public void sendMail(String to, String attachment, String subject){

        final String username = "pfeifer.artur1993@gmail.com";
        final String password = "314159abc";

        Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        // Session session = Session.getDefaultInstance(props, null);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("Bewerbung als "+subject);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(" Sehr geehrte Damen und Herren,\n\n" +
                    "über das Jobportal der Agentur für Arbeit bin ich auf Ihre Stelle aufmerksam geworden. " +
                    "Im Anhang der E-Mail finden Sie meine Bewerbungsunterlagen auf die Stelle als "+subject+"\n" +
                    "Bei Rückfragen stehe ich Ihnen gerne unter der Nummer 01772266449 zur Verfügung. Ich freue mich über die Möglichkeit Sie in einem persönlichen Gespräch überzeugen zu können.\n" +
                    "\n" +
                    "Mit freundlichem Gruß\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "Artur Pfeifer");

            MimeBodyPart attachmentBodyPart= new MimeBodyPart();

            DataSource source = new FileDataSource(attachment); // ex : "C:\\test.pdf"
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(attachment.substring(67)); // ex : "test.pdf"

            multipart.addBodyPart(textBodyPart);  // add the text part
            multipart.addBodyPart(attachmentBodyPart); // add the attachement part

            addAttachment(multipart, "/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/input/Artur_Pfeifer_Lebenslauf.pdf");
            addAttachment(multipart, "/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/input/Leistungsuebersicht.pdf");

            msg.setContent(multipart);


            Transport.send(msg);
        } catch (MessagingException e) {
            // LOGGER.log(Level.SEVERE,"Error while sending email",e);
            System.out.println("mail not send");
        }

    }

    private static void addAttachment(Multipart multipart, String filename) throws MessagingException {
        DataSource source = new FileDataSource(filename);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename.substring(66));
        multipart.addBodyPart(messageBodyPart);
    }

}
