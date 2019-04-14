package com.dragon.book.service.impl;

import com.dragon.book.mapper.BookAnalyzeMapper;
import com.dragon.book.model.TBookAnalyze;
import com.dragon.book.service.BookAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAnalyzeServiceImpl implements BookAnalyzeService {
    @Autowired
    private BookAnalyzeMapper bookAnalyzeMapper;

    public List<TBookAnalyze> getBoorowNum() {

        return bookAnalyzeMapper.selBorrowNum();
    }

    public BookAnalyzeMapper getBookAnalyzeMapper() {
        return bookAnalyzeMapper;
    }

    public void setBookAnalyzeMapper(BookAnalyzeMapper bookAnalyzeMapper) {
        this.bookAnalyzeMapper = bookAnalyzeMapper;
    }
}
