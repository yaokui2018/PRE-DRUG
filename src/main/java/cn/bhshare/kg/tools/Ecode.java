package cn.bhshare.kg.tools;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class Ecode {

    public static boolean doGet(String email,String subject, HttpServletRequest request) {
            String code = "";
            for (int i = 0; i < 5; i++) {
                    code = code + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
            }
            request.getSession().setAttribute("useremail", email);
            request.getSession().setAttribute("ecode", code);

            String myEmailAccount = "yaokui_ltd@163.com";
            String myEmailPassword = "yaokui1997";
            String myEmailSMTPHost = "smtp.163.com";
            String receiveMailAccount = email;//"1361453981@qq.com";

            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");
            final String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);
            Session session1 = Session.getInstance(props);
            session1.setDebug(true);
            MimeMessage message = new MimeMessage(session1);

            // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
            try {
                    message.setFrom(new InternetAddress(myEmailAccount, "大学乐游", "UTF-8"));

                    // 3. To: 收件人（可以增加多个收件人、抄送、密送）
                    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "", "UTF-8"));
                    message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(receiveMailAccount, "", "UTF-8")); //抄送一份
                    // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
                    message.setSubject(subject, "UTF-8");

                    StringBuffer demo = new StringBuffer();
                    demo.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
                            .append("<html>")
                            .append("<head>")
                            .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
                            .append("<title></title>")
                            .append("<style type=\"text/css\">")
                            .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}")
                            .append(".test2{font-family:\"Microsoft Yahei\";}")
                            .append("</style>")
                            .append("</head>")
                            .append("<body>")
                            .append("<span class=\"test2\">您本次的邮箱验证码是：</span>")
                            .append("<span class=\"test\">"+code+" </span> 。")
                            .append("</body>")
                            .append("</html>");
                    // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
                    message.setContent(demo.toString(), "text/html;charset=UTF-8");

                    // 6. 设置发件时间
                    message.setSentDate(new java.util.Date());

                    // 7. 保存设置
                    message.saveChanges();

                    Transport transport = session1.getTransport();
                    transport.connect(myEmailAccount, myEmailPassword);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                    return true;

            } catch (Exception e) {
                    e.printStackTrace();
            }
            return false;
    }

}
