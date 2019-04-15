package com.dragon.book.mapper.my;

import com.dragon.book.model.TBook;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.UserBorrow;

import java.util.List;

public interface MailSendDao {

    /**
     * 查询有超时图书记录的人员信息
     *
     * @return
     */
    public List<UserBorrow> findOvertimeUser();

    /**
     * 查询即将超时需要提醒用户
     *
     * @return
     */
    public List<UserBorrow> findReminderUser();

    /**
     * 查找热度排名前三且在库的图书
     */
    public List<TBook> findBook();

    /**
     * 查找没有借书的用户
     */

    public List<TSysUser> findUser();
}
