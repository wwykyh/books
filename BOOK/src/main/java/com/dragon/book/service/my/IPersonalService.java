package com.dragon.book.service.my;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookBorrow;

import java.util.List;

public interface IPersonalService {

    public TSysUser selectUserIndormation(int userid);

    public List<BookBorrow> selectBookInformation(int userid);

    public void returnBook(String isbn,int userid);

    public void renew(String isbn,int userid);

    public TBorrow borrowInfo(int id);
}
