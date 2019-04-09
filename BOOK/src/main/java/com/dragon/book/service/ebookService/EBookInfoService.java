package com.dragon.book.service.ebookService;

import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookVo;
import java.util.List;
import java.util.Map;

/**
 * user:lanwq
 * date:2019/03/18
 * desc:电子书信息展示相关类
 */
public interface EBookInfoService {
    TEBook getSingleEBook(String eBookId);

    List<TEBookVo> getTEBookVoList(Map filter);

    Integer getCount(Map filter);
}
