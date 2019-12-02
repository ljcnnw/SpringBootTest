package com.testspringboot.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 */
@Component
public class SendMailUtil {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    private static SendMailUtil sendMailUtil;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    public boolean sendMail(String to, String subject, String content) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);//发送人
            simpleMailMessage.setTo(to);//接受人
            simpleMailMessage.setSubject(subject);//主题
            simpleMailMessage.setText(content);//内容
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMailMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成随机认证码
     * @return
     */
    public String getlinkNo() {
        String linkNo = "";
        // 用字符数组的方式随机
        String model = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 36)];
            // 保证六位随机数之间没有重复的
            if (linkNo.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            linkNo = linkNo + c;
        }
        return linkNo;
    }

    /**
     *发送HTML模板
     * @param to
     * @param subject
     * @param checkNum
     * @return
     */
    public boolean sendCheckEmail(String to, String subject, String checkNum) {
        Context context = new Context();
        context.setVariable("id", checkNum);
        String emailContext = templateEngine.process("checkEmail", context);
        boolean flag = sendHtmlMail(to, subject, emailContext);
        return flag;

    }

    private SendMailUtil() {
    }

    //懒汉式
    public static synchronized SendMailUtil getInstance() {
        if (sendMailUtil == null) {
            sendMailUtil = new SendMailUtil();
        }
        return sendMailUtil;
    }

    @PostConstruct
    public void init() {
        getInstance().javaMailSender = this.javaMailSender;
        getInstance().from = this.from;
        getInstance().templateEngine = this.templateEngine;
    }


}
