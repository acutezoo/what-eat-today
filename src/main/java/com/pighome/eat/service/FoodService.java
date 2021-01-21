package com.pighome.eat.service;

import com.pighome.eat.bo.EatFoodBO;
import java.util.List;

/**
 * @author zoo
 * @date 2021/1/21
 */
public interface FoodService {

    List<EatFoodBO> getList(String userId);

    void insert(EatFoodBO foodBO);

    void update(EatFoodBO foodBO);

    void delete(Integer id);

}
