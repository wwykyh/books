package com.dragon.book.service.impl;

import com.dragon.book.mapper.TPublishMapper;
import com.dragon.book.model.TPublish;
import com.dragon.book.model.TPublishExample;
import com.dragon.book.service.PublishService;
import com.dragon.book.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNamePublishServiceImpl
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/25
 */
@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private TPublishMapper publishMapper ;

    @Override
    public List<TPublish> getPublishList() {
        TPublishExample example = new TPublishExample() ;
        return publishMapper.selectByExample(example) ;
    }

    @Override
    public PageBean getPublishListByPageBean(PageBean pageBean) {
        TPublishExample example = new TPublishExample() ;
        int count = publishMapper.countByExample(example);
        pageBean.setTotal(count);
        PageHelper.startPage(pageBean.getPage(),pageBean.getPagesize()) ;
        List<TPublish> publishList = publishMapper.selectByExample(example);
        pageBean.setRows(publishList);
        return pageBean ;
    }
    @Override
    public boolean deletePublish(String pubId){
        int row = publishMapper.deleteByPrimaryKey(Integer.parseInt(pubId)) ;
        return row > 0 ? true : false ;
    }

    @Override
    public boolean addPublish(TPublish publish) {
        int row = publishMapper.insert(publish) ;
        return row > 0 ? true : false ;
    }

    @Override
    public boolean updatePublish(TPublish publish) {
        int row = publishMapper.updateByPrimaryKey(publish) ;
        return row > 0 ? true : false ;
    }

    @Override
    public TPublish getPublishById(String pubId) {
        return publishMapper.selectByPrimaryKey(Integer.parseInt(pubId));
    }
}
