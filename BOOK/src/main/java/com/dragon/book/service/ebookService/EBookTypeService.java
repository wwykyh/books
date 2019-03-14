package com.dragon.book.service.ebookService;

import com.dragon.book.model.TEbookType;
import com.dragon.book.util.PageBean;

import java.util.List;

/**
 * user:lanwq
 * desc:电子书类型管理类接口
 * date:2019/03/14
 */

public interface EBookTypeService {
    // 获取所有的电子书类型
    List<TEbookType> getTEbookTypelist();

    TEbookType getSingleTEbookType(Integer ebookTypeId);

    boolean updateTEbookType(TEbookType tEbookType);

    boolean addTEbookType(TEbookType tEbookType);

    boolean delTEbookType(Integer ebookTypeId);

    // 分页查询电子书类型
    PageBean getTEbookTypePageByPageBean(PageBean pageBean);


}
