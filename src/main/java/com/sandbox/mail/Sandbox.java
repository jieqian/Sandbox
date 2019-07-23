package com.sandbox.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class Sandbox {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.host", "smtp.exmail.qq.com");
        prop.put("mail.smtp.port", "465");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);


        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        // 第三个参数为授权码，具体参考https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256
        ts.connect("smtp.exmail.qq.com","devops@xxx.cn", "xxxxxxxxxx");
        // 创建邮件
        Message message = createSimpleMail(session);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public static MimeMessage createSimpleMail(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("devops@xxx.cn"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("jay@xxx.cn"));
        // 邮件的标题
        message.setSubject("JavaMail测试");
        // 邮件的文本内容
        message.setContent("JavaMail发送邮件成功！", "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
