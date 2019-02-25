package com.dragon.book.service;

import com.dragon.book.model.TType;
import com.dragon.book.pojo.PageBean;

import java.util.List;

public interface TypeService {

    /**
     * 通过类型名称查询类型对象
     * @param typeName
     * @return TType
     */
    TType getTypeByName(String typeName);

    /**
     * 查询所有类型对象列表
     * @return List<TType>
     */
    List<TType> getTypeList();

    /**
     * 分页查询出版社信息
     * @param pageBean
     * @return pageBean
     */
    PageBean getTypePageByPageBean(PageBean pageBean);

    /**
     *  通过ID删除出版社信息
     * @param typeId
     * @return boolean
     */
    boolean deleteTypeByTyepId(String typeId) ;

    /**
     * 通过id获得的一个出版社信息
     * @param typeId
     * @return TType
     */
    TType getTypeByTypeId(String typeId) ;

    /**
     *  添加出版社信息
     * @param type
     * @return boolean
     */
    boolean addType(TType type) ;

    /**
     * 更新出版社信息
     * @param type
     * @return boolean
     */
    boolean updateType(TType type) ;

}
