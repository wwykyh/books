package com.dragon.book.service.mail;


import com.dragon.book.model.MailParameter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * 发送邮件
 */

public class DoSendCCEmail {

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    public void sendEmail(JavaMailSender sender, MailParameter mailParameter, String templatePath) {
        try {
            //Configurationz配置
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
//            cfg.setClassForTemplateLoading(getClass(),"../../../../../../resources/template");
//            cfg.setServletContextForTemplateLoading(session.getServletContext(),"/resources/template");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            //将List邮箱转为数组对象
            String[] toMails = new String[mailParameter.getToMailList().size()];
            mailParameter.getToMailList().toArray(toMails);

            //邮件发送配置,使用抄送发送
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom(mailParameter.getFromMail());
            //message.setTo(toMails);
            message.setCc(toMails);
            message.setSubject(mailParameter.getMailName());

            //设置模板参数
            String currentDate = LocalDate.now().toString();
            Map<String, Object> model = new HashMap<String, Object>();
            if (mailParameter.getBooks().size() == 1) {
                model.put("bookName", mailParameter.getBooks().get(0).getSm());
                model.put("jj", mailParameter.getBooks().get(0).getJj());
                model.put("picture", mailParameter.getBooks().get(0).getPicture());

                model.put("bookNameB", "");
                model.put("jjB", "");
                model.put("pictureB", "");

                model.put("bookNameC", "");
                model.put("jjC", "");
                model.put("pictureC", "");

            } else if (mailParameter.getBooks().size() == 2) {
                model.put("bookNameA", mailParameter.getBooks().get(0).getSm());
                model.put("jjA", mailParameter.getBooks().get(0).getJj());
                model.put("pictureA", mailParameter.getBooks().get(0).getPicture());

                model.put("bookNameB", mailParameter.getBooks().get(1).getSm());
                model.put("jjB", mailParameter.getBooks().get(1).getJj());
                model.put("pictureB", mailParameter.getBooks().get(1).getPicture());

                model.put("bookNameC", "");
                model.put("jjC", "");
                model.put("pictureC", "");

            } else if (mailParameter.getBooks().size() == 3) {
                model.put("bookNameA", mailParameter.getBooks().get(0).getSm());
                model.put("jjA", mailParameter.getBooks().get(0).getJj());
                model.put("pictureA", mailParameter.getBooks().get(0).getPicture());

                model.put("bookNameB", mailParameter.getBooks().get(1).getSm());
                model.put("jjB", mailParameter.getBooks().get(1).getJj());
                model.put("pictureB", mailParameter.getBooks().get(1).getPicture());

                model.put("bookNameC", mailParameter.getBooks().get(2).getSm());
                model.put("jjC", mailParameter.getBooks().get(2).getJj());
                model.put("pictureC", mailParameter.getBooks().get(2).getPicture());
            }
            model.put("url", mailParameter.getUrl());
            model.put("date", LocalDate.now());

            try {
                Template temp = cfg.getTemplate(mailParameter.getTemplate());
                String text = FreeMarkerTemplateUtils.processTemplateIntoString(temp, model);
                message.setText(text, true);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}