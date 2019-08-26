package com.cqwo.xxx.core.helper.mailpop3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.mail.util.MimeMessageParser;

import javax.activation.DataSource;
import javax.mail.Address;
import java.util.List;

/**
 * 说明:
 *
 * @author margergoo@gmail.com
 * @date 2019/4/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {
    // 获取发件人地址
    private String fromAddr;
    // 获取抄送人地址
    private List<Address> cc;
    // 获取收件人地址
    private List<Address> to;
    // 获取回复邮件时的收件人
    private String replyTo;
    // 获取邮件主题
    private String subject;
    // 获取Html内容
    private String htmlContent;
    // 获取纯文本邮件内容（注：有些邮件不支持html）
    private String plainContent;
    //附件
    private List<DataSource> attachments;

    public MailInfo(MimeMessageParser parser) throws Exception {
        this.fromAddr = parser.getFrom();
        this.cc = parser.getCc();
//        this.to = parser.getTo();
        this.replyTo = parser.getReplyTo();
        this.subject = parser.getSubject();
        this.htmlContent = parser.getHtmlContent();
        this.plainContent = parser.getPlainContent();
        this.attachments = parser.getAttachmentList();
    }
}
