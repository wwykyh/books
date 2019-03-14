package com.dragon.book.service.impl;

import com.dragon.book.mapper.TEbookTypeMapper;
import com.dragon.book.model.TEbookType;
import com.dragon.book.model.TEbookTypeExample;
import com.dragon.book.service.ebookService.EBookTypeService;
import com.dragon.book.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EBookTypeServiceImpl implements EBookTypeService {

    @Autowired
    private TEbookTypeMapper tEbookTypeMapper;
    @Override
    public List<TEbookType> getTEbookTypelist() {
        return tEbookTypeMapper.selectByExample(new TEbookTypeExample());
    }

    @Override
    public TEbookType getSingleTEbookType(Integer ebookTypeId) {
        return tEbookTypeMapper.selectByPrimaryKey(ebookTypeId);
    }

    @Override
    public boolean updateTEbookType(TEbookType tEbookType) {
        return tEbookTypeMapper.updateByPrimaryKey(tEbookType) > 0;
    }

    @Override
    public boolean addTEbookType(TEbookType tEbookType) {
        return tEbookTypeMapper.insert(tEbookType) > 0;
    }

    @Override
    public boolean delTEbookType(Integer ebookTypeId) {
        return tEbookTypeMapper.deleteByPrimaryKey(ebookTypeId) > 0;
    }

    @Override
    public PageBean getTEbookTypePageByPageBean(PageBean pageBean) {
        TEbookTypeExample example = new TEbookTypeExample() ;
        int total = tEbookTypeMapper.countByExample(example) ;
        pageBean.setTotal(total) ;
        PageHelper.startPage(pageBean.getPage(),pageBean.getPagesize()) ;
        List<TEbookType> tEbookTypes = tEbookTypeMapper.selectByExample(example) ;
        pageBean.setRows(tEbookTypes) ;
        return pageBean;
    }
}
