/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Subscriber;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    private static Mail instance;

    public static Mail getInstance() {

        if (instance == null) {
            instance = new Mail();
        }
        return instance;
    }

    public void SendWelcomeMail(Subscriber s) {
        
        final String username = "pi.phoenix.2019@gmail.com";
        final String password = "phoenixpi2019";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(s.getEmail()));

            message.setSubject("Welcome to our NEWSLETTER");

            // Send the actual HTML message, as big as you like
            message.setContent(
                    "<link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n"
                    + "<script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js\"></script>\n"
                    + "<script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n"
                    + "\n"
                    + "<div style=\"font-family: Helvetica Neue, Helvetica, Helvetica, Arial, sans-serif;\">\n"
                    + "    <table style=\"width: 100%;\">\n"
                    + "        <tr>\n"
                    + "            <td>TechEvent</td>\n"
                    + "            <td bgcolor=\"#FFFFFF \">\n"
                    + "                <div style=\"padding: 15px; max-width: 600px;margin: 0 auto;display: block; border-radius: 0px;padding: 0px; border: 1px solid lightseagreen;\">\n"
                    + "                    <table style=\"width: 100%;background: #00b6e2 ;\">\n"
                    + "                        <tr>\n"
                    + "                            <td></td>\n"
                    + "                            <td>\n"
                    + "                                <div>\n"
                    + "                                    <table width=\"100%\">\n"
                    + "                                        <tr>\n"
                    + "                                            <td rowspan=\"2\" style=\"text-align:center;padding:10px;\">\n"
                    + "                                                <span style=\"color:white;float:right;font-size: 13px;font-style: italic;margin-top: 20px; padding:10px; font-size: 14px; font-weight:normal;\">\n"
                    + "							                    <span></span></span></td>\n"
                    + "                                        </tr>\n"
                    + "                                    </table>\n"
                    + "                                </div>\n"
                    + "                            </td>\n"
                    + "                            <td></td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                    <table style=\"padding: 10px;font-size:14px; width:100%;\">\n"
                    + "                        <tr>\n"
                    + "                            <td style=\"padding:10px;font-size:14px; width:100%;\">\n"
                    + "                                <p>Welcome,</p>\n"
                    + "                                <p><br /> Thanks for subscribing to our newsletter!</p>\n"
                    + "                                <p>\n"
                    + "                                    <a href=\"http://localhost/TechEvent/web/news/sub/update/" + s.getIdSubscriber() + "\">Update</a>\n"
                    + "                                    <a href=\"http://localhost/TechEvent/web/news/sub/removeVisitor/" + s.getIdSubscriber() + "\">unsubscribe</a>\n"
                    + "                                </p>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </div>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "    </table>\n"
                    + "</div>",
                    "text/html"
            );

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
