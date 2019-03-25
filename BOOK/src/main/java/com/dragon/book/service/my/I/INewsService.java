package com.dragon.book.service.my.I;

import com.dragon.book.model.TBookNews;
import com.dragon.book.model.TBorrow;

import java.util.List;

public interface INewsService {

    public List<TBookNews> getNews(int userid);

    public void deleteNews(int id);

    public TBorrow findDetailInfo(int isbn, int uid);

}
