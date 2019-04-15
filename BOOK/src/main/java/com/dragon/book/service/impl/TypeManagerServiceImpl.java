package com.dragon.book.service.impl;

import com.dragon.book.mapper.TTypeMapper;
import com.dragon.book.model.TType;
import com.dragon.book.model.TTypeExample;
import com.dragon.book.service.TypeManagerService;
import com.dragon.book.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNameTypeManagerServiceImpl
 * @Description TODO
 * @Author liulei
 * @Date 2019/2/28
 */
@Service
public class TypeManagerServiceImpl implements TypeManagerService {
    @Autowired
    private TTypeMapper tTypeMapper;

    @Override
    public TType getTypeByName(String typeName) {
        TTypeExample example = new TTypeExample();
        TTypeExample.Criteria criteria = example.createCriteria();
        criteria.andLxmcEqualTo(typeName);
        List<TType> list = tTypeMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public List<TType> getTypeList() {
        TTypeExample example = new TTypeExample();
        List<TType> types = tTypeMapper.selectByExample(example);
        return types;
    }

    @Override

    public PageBean getTypePageByPageBean(PageBean pageBean) {
        TTypeExample example = new TTypeExample();
        int count = tTypeMapper.countByExample(example);
        pageBean.setTotal(count);
        PageHelper.startPage(pageBean.getPage(), pageBean.getPagesize());
        List<TType> tTypes = tTypeMapper.selectByExample(example);
        pageBean.setRows(tTypes);
        return pageBean;
    }

    @Override
    public boolean deleteTypeByTyepId(String typeId) {
        int rows = tTypeMapper.deleteByPrimaryKey(Integer.parseInt(typeId));
        return rows > 0 ? true : false;
    }

    @Override
    public TType getTypeByTypeId(String typeId) {
        return tTypeMapper.selectByPrimaryKey(Integer.parseInt(typeId));
    }

    @Override
    public boolean addType(TType type) {
        int rows = tTypeMapper.insert(type);
        return rows > 0 ? true : false;
    }

    @Override
    public boolean updateType(TType type) {
        int rows = tTypeMapper.updateByPrimaryKey(type);
        return rows > 0 ? true : false;
    }


    public TTypeMapper gettTypeMapper() {
        return tTypeMapper;
    }

    public void settTypeMapper(TTypeMapper tTypeMapper) {
        this.tTypeMapper = tTypeMapper;
    }
}
