package com.dragon.book.service;

import com.dragon.book.model.TPublish;
import com.dragon.book.util.PageBean;

import java.util.List;

public interface PublishService {

    /**
     * 查询所有出版社信息列表
     * @return List<TPublish>
     */
    List<TPublish> getPublishList() ;

    /**
     * 分页查询出版社信息
     * @param pageBean
     * @return PageBean
     */
    PageBean getPublishListByPageBean(PageBean pageBean) ;

    /**
     * 通过ID删除出版社信息
     * @param pubId
     * @return boolean
     */
    boolean deletePublish(String pubId);

    /**
     * 添加出版社信息
     * @param publish
     * @return boolean
     */
    boolean addPublish(TPublish publish);

    /**
     * 更新出版社信息
     * @param publish
     * @return boolean
     */
    boolean updatePublish(TPublish publish);

    /**
     * 通过id获得的一个出版社信息
     * @param pubId
     * @return TPublish
     */
    TPublish getPublishById(String pubId) ;
}
