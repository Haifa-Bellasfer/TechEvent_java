/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Article;
import entity.Domain;
import entity.Newsletter;
import entity.NewsletterSubscriber;
import entity.Subscriber;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.ArticleService;
import service.DomainService;
import service.NewsletterService;
import service.NewsletterSubscriberService;
import service.SubscriberService;

public class Mail {

    private static Mail instance;
    final String username = "pi.phoenix.2019@gmail.com";
    final String password = "phoenixpi2019";

    public static Mail getInstance() {

        if (instance == null) {
            instance = new Mail();
        }
        return instance;
    }

    public void SendWelcomeMail(Subscriber s) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
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

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean SendNewsletter() {
        boolean sent = false;
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        subscribers.addAll(SubscriberService.getInstance().DisplayAll());
        ArrayList<Domain> domains = new ArrayList<>();
        domains.addAll(DomainService.getInstance().DisplayAll());
        for (Domain domain : domains) {
            ArrayList<Article> articles = new ArrayList<>();
            articles.addAll(ArticleService.getInstance().getArticleByDomain(domain));
            if (articles.size() > 0) {
                sent = true;
                Newsletter newsletter = new Newsletter();
                newsletter.setCreationDate(new Date(System.currentTimeMillis()));
                newsletter.setIdNewsletter(NewsletterService.getInstance().create(newsletter));
                for (Subscriber sub : subscribers) {
                    if (sub.getDomain().equals(domain)) {
                        sendNewsletterHtml(sub, articles, newsletter);
                        NewsletterSubscriber ns = new NewsletterSubscriber();
                        ns.setNewsletter(newsletter);
                        ns.setSubscriber(sub);
                        NewsletterSubscriberService.getInstance().insert(ns);
                    }
                }
                for (Article article : articles) {
                    article.setNewsletter(newsletter);
                    ArticleService.getInstance().updateNewsletter(article);
                }
            }
        }
        return sent;
    }

    public void sendNewsletterHtml(Subscriber sub, ArrayList<Article> articles, Newsletter newsletter) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sub.getEmail()));

            message.setSubject("Newsletter : ");

            String mail = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <title>Newsletter</title>\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "    <link href=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                    + "    <style type=\"text/css\">\n"
                    + "    </style>\n"
                    + "    <script src=\"http://code.jquery.com/jquery-1.11.1.min.js\"></script>\n"
                    + "    <script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js\"></script>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "<div class=\"m_5772815688879916528tableWhite\" style=\"width:100%!important;background:#e6e7e8;padding-bottom:10px\">\n"
                    + "    <table class=\"m_5772815688879916528tableWide\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#e6e7e8\">\n"
                    + "        <tbody>\n"
                    + "        <tr>\n"
                    + "            <td class=\"m_5772815688879916528tableWhiteBackground\" style=\"border-collapse:collapse\" width=\"100%\" bgcolor=\"#e6e7e8\" valign=\"top\" align=\"center\">\n"
                    + "                <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                    <tbody>\n"
                    + "                    <tr>\n"
                    + "                        <td class=\"m_5772815688879916528hide\" style=\"border-collapse:collapse\" width=\"50\" height=\"70\" bgcolor=\"#FFFFFF\"><img style=\"outline:none;text-decoration:none;display:block\" src=\"https://ci3.googleusercontent.com/proxy/CUlhUDkF0GhJXvMi8k-tsT6O7DUlj7C8S7VO5whfkhSbnD1s2cg9dWSFa60WI9w1LPbGaXkWeobneAv9dcnymkKFjq52KpJvc5i_35eb7q4MYCnwC06sPJoDYcEGgNTiljYEUtXI9DYIsE9itgN9TW9QHEbXgzL5JldO6QvnoiBS2rXPjzrHomoX=s0-d-e1-ft#https://static.cdn.responsys.net/i2/responsysimages/survey/contentlibrary/2017_q4/newsletter_wufoo/images/spacer.gif\" width=\"50\" height=\"70\" alt=\"\" class=\"CToWUd\">\n"
                    + "                        </td>\n"
                    + "                        <td style=\"border-collapse:collapse\" height=\"70\" bgcolor=\"#ffffff\" class=\"m_5772815688879916528hide\" align=\"left\" valign=\"middle\">\n"
                    + "                            <font style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#a4a4a4;font-weight:600\">Newsletter</font>\n"
                    + "                            <font style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#a4a4a4;font-weight:300\">" + newsletter.getCreationDate() + "</font>\n"
                    + "                        </td>\n"
                    + "                        <td style=\"border-collapse:collapse\" class=\"m_5772815688879916528table\" width=\"219\" height=\"90\" bgcolor=\"#ffffff\" align=\"left\" valign=\"middle\">\n"
                    + "                        </td>\n"
                    + "                        <td class=\"m_5772815688879916528signin\" style=\"border-collapse:collapse\" align=\"right\" valign=\"middle\" width=\"85\" bgcolor=\"#ffffff\">\n"
                    + "                        </td>\n"
                    + "                        <td class=\"m_5772815688879916528hide\" style=\"border-collapse:collapse\" width=\"50\" height=\"70\" bgcolor=\"#FFFFFF\"><img style=\"outline:none;text-decoration:none;display:block\">\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    + "                    </tbody>\n"
                    + "                </table>\n";

            for (int i = 0; i < articles.size(); i++) {
                if (i / 2 != 0) {

                    mail = mail
                            + "                        <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                            <tbody>\n"
                            + "                            <tr>\n"
                            + "                                <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse;padding-top:50px\" align=\"center\" valign=\"top\" bgcolor=\"#3333ff\">\n"
                            + "                                    <table class=\"m_5772815688879916528table90\" width=\"450\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                                        <tbody>\n"
                            + "                                        <tr>\n"
                            + "                                            <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse\" width=\"450\" height=\"3\" align=\"center\" valign=\"top\" bgcolor=\"#3333ff\">\n"
                            + "                                                <a href=\"http://localhost/TechEvent/web/news/front/article/show/" + articles.get(i).getIdArticle() + " \" style=\"line-height:39px;text-decoration:none;color:#ffffff;font-size:35px;font-weight:bold;font-family:helvetica,arial,sans-serif\">\n"
                            + articles.get(i).getTitreArticle() + "</a>\n"
                            + "                                            </td>\n"
                            + "                                        </tr>\n"
                            + "                                        </tbody>\n"
                            + "                                    </table>\n"
                            + "                                </td>\n"
                            + "                            </tr>\n"
                            + "                            </tbody>\n"
                            + "                        </table>\n"
                            + "                        <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                            <tbody>\n"
                            + "                            <tr>\n"
                            + "                                <td class=\"m_5772815688879916528tableCenterPad\" style=\"border-collapse:collapse;padding-top:15px\" align=\"center\" valign=\"top\" bgcolor=\"#3333ff\">\n"
                            + "                                    <a href=\"http://localhost/TechEvent/web/news/front/article/show/" + articles.get(i).getIdArticle() + " \" style=\"color:#ffffff;font-family:Arial,Helvetica,sans-serif;font-size:14px;line-height:32px;outline:none;white-space:nowrap;border-radius:2px;text-decoration:none;display:inline-block;border:1px solid #6af26e;background-color:#4d4dff\">      READ MORE      </a>\n"
                            + "                                </td>\n"
                            + "                            </tr>\n"
                            + "                            </tbody>\n"
                            + "                        </table>\n"
                            + "                        <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                            <tbody>\n"
                            + "                            <tr>\n"
                            + "                                <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse;padding-bottom:50px;padding-top:10px\" align=\"center\" valign=\"top\" bgcolor=\"#3333ff\">\n"
                            + "                                    <table class=\"m_5772815688879916528table90\" width=\"80%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                                    </table>\n"
                            + "                                </td>\n"
                            + "                            </tr>\n"
                            + "                            </tbody>\n"
                            + "                        </table>\n";
                } else {

                    mail = mail
                            + "                        <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                            <tbody>\n"
                            + "                            <tr>\n"
                            + "                                <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse;padding-top:50px\" align=\"center\" valign=\"top\" bgcolor=\"#38a88c\">\n"
                            + "                                    <table class=\"m_5772815688879916528table90\" width=\"450\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                                        <tbody>\n"
                            + "                                        <tr>\n"
                            + "                                            <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse\" width=\"450\" height=\"3\" align=\"center\" valign=\"top\" bgcolor=\"#38a88c\">\n"
                            + "                                                <a href=\"http://localhost/TechEvent/web/news/front/article/show/" + articles.get(i).getIdArticle() + " \" style=\"line-height:39px;text-decoration:none;color:#ffffff;font-size:35px;font-weight:bold;font-family:helvetica,arial,sans-serif\">\n"
                            + articles.get(i).getTitreArticle() + "\n"
                            + "                                            </td>\n"
                            + "                                        </tr>\n"
                            + "                                        </tbody>\n"
                            + "                                    </table>\n"
                            + "                                </td>\n"
                            + "                            </tr>\n"
                            + "                            </tbody>\n"
                            + "                        </table>\n"
                            + "                        <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                            <tbody>\n"
                            + "                            <tr>\n"
                            + "                                <td class=\"m_5772815688879916528tableCenterPad\" style=\"border-collapse:collapse;padding-top:15px\" align=\"center\" valign=\"top\" bgcolor=\"#38a88c\">\n"
                            + "                                    <a href=\"http://localhost/TechEvent/web/news/front/article/show/" + articles.get(i).getIdArticle() + " \" style=\"color:#ffffff;font-family:Arial,Helvetica,sans-serif;font-size:14px;line-height:32px;outline:none;white-space:nowrap;border-radius:2px;text-decoration:none;display:inline-block;border:1px solid #6af26e;background-color:#25d670\">      READ MORE      </a>\n"
                            + "                                </td>\n"
                            + "                            </tr>\n"
                            + "                            </tbody>\n"
                            + "                        </table>\n"
                            + "                        <table class=\"m_5772815688879916528table\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                            <tbody>\n"
                            + "                            <tr>\n"
                            + "                                <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse;padding-bottom:50px;padding-top:10px\" align=\"center\" valign=\"top\" bgcolor=\"#38a88c\">\n"
                            + "                                    <table class=\"m_5772815688879916528table90\" width=\"80%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                            + "                                    </table>\n"
                            + "                                </td>\n"
                            + "                            </tr>\n"
                            + "                            </tbody>\n"
                            + "                        </table>\n";
                }
            }

            mail = mail
                    + "                <table class=\"m_5772815688879916528hide\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                    <tbody>\n"
                    + "                    <tr>\n"
                    + "                        <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse\" bgcolor=\"#f6f9fb\" align=\"center\">\n"
                    + "                            <br>\n"
                    + "                            <table class=\"m_5772815688879916528hide\" width=\"500\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                                <tbody>\n"
                    + "                                <tr>\n"
                    + "                                    <td class=\"m_5772815688879916528table\" style=\"border-collapse:collapse\" bgcolor=\"#f6f9fb\" align=\"center\">\n"
                    + "                                        <table class=\"m_5772815688879916528hide\" width=\"220\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\">\n"
                    + "                                            <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td style=\"border-collapse:collapse;line-height:14px\" align=\"left\" valign=\"top\">\n"
                    + "                                                    <font style=\"font-family:arial;font-size:11px;line-height:14px;color:#999999\">\n"
                    + "                                                        This e-mail was sent to <font style=\"font-family:arial;font-size:11px;line-height:14px;color:#999999\"><a style=\"color:#999999;text-decoration:none\">" + sub.getEmail() + "</a></font>. You received this email because you signed up for a TechEvent newsletter.\n"
                    + "                                                        <br>\n"
                    + "                                                        <br>\n"
                    + "                                                        <a href=\"http://localhost/TechEvent/web/news/sub/removeVisitor/" + sub.getIdSubscriber() + " \" style=\"font-family:arial;font-size:11px;color:#999999;font-weight:bold;text-decoration:none\">Unsubscribe</a>   |   \n"
                    + "                                                        <a href=\"http://localhost/TechEvent/web/news/sub/update/" + sub.getIdSubscriber() + " \" style=\"font-family:arial;font-size:11px;color:#999999;text-decoration:none\">Update</a>\n"
                    + "                                                        <br>\n"
                    + "                                                        <br>\n"
                    + "                                                    </font>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            </tbody>\n"
                    + "                                        </table>\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                </tbody>\n"
                    + "                            </table>\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    + "                    </tbody>\n"
                    + "                </table>\n"
                    + "            </td>\n"
                    + "        </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "    <br>\n"
                    + "</div>\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";

            // Send the actual HTML message, as big as you like
            message.setContent(mail, "text/html");

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
