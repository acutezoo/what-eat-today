package com.pighome.eat.service;

import com.pighome.eat.bo.EatClassificationBO;
import java.util.List;

/**
 * @author zoo
 * @date 2021/1/21
 */
public interface ClassifyService {

    List<EatClassificationBO> getList(String userId);

    void insert(EatClassificationBO eatClassificationBO);

    void update(EatClassificationBO eatClassificationBO);

    void delete(Integer id);

}
