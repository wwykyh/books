package com.dragon.book.service.impl;

import com.dragon.book.mapper.EbookInfoMapper;
import com.dragon.book.mapper.TEBookMapper;
import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookVo;
import com.dragon.book.service.ebookService.EBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author；lanwenquan
 * @Description:电子书的前台显示信息实现类
 */
@Service
public class EBookInfoServiceImpl implements EBookInfoService {

    @Autowired
    private TEBookMapper teBookMapper;

    @Autowired
    private EbookInfoMapper ebookInfoMapper;


    @Override
    public TEBook getSingleEBook(String eBookId) {
        return teBookMapper.selectByPrimaryKey(eBookId);
    }

    @Override
    public List<TEBookVo> getTEBookVoList(Map filter) {
        return ebookInfoMapper.queryTEBookVo(filter);
    }

    @Override
    public Integer getCount(Map filter) {
        return ebookInfoMapper.countTEBookVo(filter);
    }
}
