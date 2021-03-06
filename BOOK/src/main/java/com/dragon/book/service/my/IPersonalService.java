package com.dragon.book.service.my;

import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookBorrow;

import java.util.List;

public interface IPersonalService {

    public TSysUser selectUserIndormation(int userId);

    public List<BookBorrow> selectBookInformation(int userId);

    public void returnBook(String isbn, int userId);

    public void renew(String isbn, int userId);

    public TBorrow borrowInfo(int id);
}
