/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by cqnews on 2017/4/16.
 */
public class MailHelper {
    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "smtp.exmail.qq.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private String sendUser = "1311535288@qq.com";
    private String sendUname = "1311535288";
    private String sendPwd = "19891228?luo";
    // 建立会话
    private MimeMessage message;
    private Session s;

    /*
     * 初始化方法
     */
    public MailHelper() {
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");
        //props.put("mail.smtp.auth", "true");
        s = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendUname, sendPwd);
            }
        });
        s.setDebug(true);
        message = new MimeMessage(s);
    }

    /**
     * 发送邮件
     *
     * @param headName    邮件头文件名
     * @param sendHtml    邮件内容
     * @param receiveUser 收件人地址
     */
    public void doSendHtmlEmail(String headName, String sendHtml,
                                String receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sendUser);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, sendUname, sendPwd);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close(); //System.out.println("send success!");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MailHelper se = new MailHelper();
        se.doSendHtmlEmail("邮件头文件名", "邮件内容", "3601927934@qq.com");
    }

}
