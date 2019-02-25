package com.dragon.book.service.impl;

import com.dragon.book.mapper.TTypeMapper;
import com.dragon.book.model.TType;
import com.dragon.book.model.TTypeExample;
import com.dragon.book.pojo.PageBean;
import com.dragon.book.service.TypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNameTypeServiceImpl
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/24
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TTypeMapper typeMapper ;
    @Override
    public TType getTypeByName(String typeName) {
        TTypeExample example = new TTypeExample() ;
        TTypeExample.Criteria criteria = example.createCriteria();
        criteria.andLxmcEqualTo(typeName) ;
        List<TType> list = typeMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public List<TType> getTypeList() {
        TTypeExample example = new TTypeExample() ;
        List<TType> types = typeMapper.selectByExample(example);
        return types;
    }

    @Override
    public PageBean getTypePageByPageBean(PageBean pageBean) {
        TTypeExample example = new TTypeExample() ;
        int count = typeMapper.countByExample(example) ;
        pageBean.setTotal(count) ;
        PageHelper.startPage(pageBean.getPage(),pageBean.getPagesize()) ;
        List<TType> tTypes = typeMapper.selectByExample(example) ;
        pageBean.setRows(tTypes) ;
        return pageBean ;
    }

    @Override
    public boolean deleteTypeByTyepId(String typeId) {
        int rows = typeMapper.deleteByPrimaryKey(Integer.parseInt(typeId));
        return rows > 0 ? true : false ;
    }

    @Override
    public TType getTypeByTypeId(String typeId) {
        return typeMapper.selectByPrimaryKey(Integer.parseInt(typeId));
    }

    @Override
    public boolean addType(TType type) {
        int rows = typeMapper.insert(type) ;
        return rows > 0 ? true : false ;
    }

    @Override
    public boolean updateType(TType type) {
        int rows = typeMapper.updateByPrimaryKey(type);
        return rows > 0 ? true : false ;
    }
}
