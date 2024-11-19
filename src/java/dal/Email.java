package dal;

import java.util.Date;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Email {

    private static final String from = "group4.jobconnector@gmail.com";
    private static final String password = "qoie zkim chza alcg";

    public static void SendEmails(String to, String subject, String noidung) {

        // Khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //smtp host
        props.put("mail.smtp.port", "587"); //TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");//auth
        props.put("mail.smtp.starttls.enable", "true");

        //create auhthenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        //Phiên làm việc
        Session session = Session.getInstance(props, auth);
        //Gui email đến ai

        //Tạo tin nhắn  
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-");
            //Người gửi
            msg.setFrom(from);
            //Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //Tiêu đề
            msg.setSubject(subject);
            //Quy định ngày gửi
            msg.setSentDate(new Date());
            //Quy định email nhận phản hồi
            //msg.setReplyTo(InternetAddress.parse(from,false))
            //Nội dung
            msg.setText(noidung, "UTF-8");
            //Gửi email
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
        // Thiết lập các thông tin để gửi email kiểm thử
        String to = "ntanh2811@gmail.com"; // Địa chỉ email người nhận kiểm thử
        String subject = "Test Email - Activation Link"; // Tiêu đề email kiểm thử
        String noidung = "Dear User,\n\nThis is a test email to verify the functionality of the email sending system.\n\nRegards,\nTest Team";

        // Gọi phương thức gửi email
        try {
            Email.SendEmails(to, subject, noidung);
            System.out.println("Test passed: Email sent successfully to " + to);
        } catch (Exception e) {
            System.out.println("Test failed: Error occurred while sending email");
            e.printStackTrace();
        }
    }
}
