package com.pighome.eat.service;

import com.pighome.eat.bo.EatFoodBO;
import com.pighome.eat.bo.EatRandomSettingBO;
import java.util.List;

/**
 * @author zoo
 * @date 2021/1/21
 */
public interface RandomService {

    List<EatRandomSettingBO> getList(String userId);

    void insert(EatRandomSettingBO settingBO);

    void update(EatRandomSettingBO settingBO);

    void delete(Integer id);

    List<EatFoodBO> randomGet(String userId);
}
