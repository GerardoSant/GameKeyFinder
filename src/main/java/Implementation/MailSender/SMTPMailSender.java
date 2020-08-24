package Implementation.MailSender;

import Model.Game.GameSale;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class SMTPMailSender implements MailSender{

    private static final String SUBJECT = "CheapKeyFinder Notification - Some of your games are at your desired price!";
    private static final String HOST = "mail.smtp.host";
    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String PORT = "mail.smtp.port";
    private static final String PORTNUMBER = "587";
    private static final String AUTHENTICATION = "mail.smtp.auth";
    private static final String STARTTLS = "mail.smtp.starttls.enable";
    private static final String AUTHENTICATION_ENABLE = "true";
    private static final String STARTTLS_ENABLE = "true";
    private static final String USER = "insert your email account here";
    private static final String PASSWORD = "insert your email password here";
    private static final String TEMPLATE = "The price of the following games on your list are at the price you are looking for: \n";

    private String mailTo;

    public SMTPMailSender(String mailTo) {
        this.mailTo = mailTo;
    }

    @Override
    public void send(List<GameSale> list) throws SendFailedException {
        String msg = TEMPLATE;
        msg = setBody(list, msg);
        Properties props = getProperties();
        Session session = getSession(props);
        try {
            MimeMessage message = setMessage(msg, session);
            Transport.send(message);
            System.out.println("Message sent successfully!");
        } catch (SendFailedException e){
            System.out.println("Invalid Address");
            throw e;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch(Exception e){
            System.out.println("Couldn't send email to " + mailTo + " , probably is wrong address");
        }
    }

    private MimeMessage setMessage(String msg, Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));
        message.setSubject(SUBJECT);
        message.setText(msg);
        return message;
    }

    private Session getSession(Properties props) {
        return Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER, PASSWORD);
                    }
                });
    }

    private String setBody(List<GameSale> list, String msg) {
        for (GameSale i : list){
            msg += i.getName() + " -> " + i.getPrice() + "€, Link: " + i.getLink() + "\n";
        }
        return msg;
    }

    private Properties getProperties() {
        Properties props  = new Properties();
        props.put(HOST, HOSTNAME); //SMTP Host
        props.put(PORT, PORTNUMBER); //TLS Port
        props.put(AUTHENTICATION, AUTHENTICATION_ENABLE); //enable authentication
        props.put(STARTTLS, STARTTLS_ENABLE); //enable STARTTLS
        return props;
    }

    public String getMailTo() {
        return mailTo;
    }
}
