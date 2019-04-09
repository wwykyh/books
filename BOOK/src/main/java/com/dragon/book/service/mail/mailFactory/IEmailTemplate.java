package com.dragon.book.service.mail.mailFactory;


import com.dragon.book.model.MailParameter;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TSysUser;

import java.util.List;

public interface IEmailTemplate {

    /**
     * 收件人to
     * @param to
     */
    public MailParameter createParameter(String fromMail, String to, String userName, String bookName);

    /**
     * 多个收件人
     * @param fromMail
     * @param books
     * @param users
     * @return
     */
    public MailParameter createParameter(String fromMail, List<TBook> books, List<TSysUser> users);

}
