package com.pighome.eat.service.impl;

import com.pighome.eat.bo.EatFoodBO;
import com.pighome.eat.dao.EatClassificationMapper;
import com.pighome.eat.dao.EatFoodMapper;
import com.pighome.eat.dataobject.EatClassificationDO;
import com.pighome.eat.dataobject.EatFoodDO;
import com.pighome.eat.service.FoodService;
import com.pighome.eat.utils.BeanCopierUtils;
import com.pighome.eat.utils.StreamUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zoo
 * @date 2021/1/21
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    EatFoodMapper eatFoodMapper;
    @Autowired
    EatClassificationMapper eatClassificationMapper;

    @Override
    public List<EatFoodBO> getList(String userId) {
        Map<Integer, String> classifyNameMap = StreamUtil
                .listToMap(eatClassificationMapper.select(EatClassificationDO.builder().userId(userId).build()), EatClassificationDO::getId, EatClassificationDO::getName);
        classifyNameMap.put(0, "默认");
        List<EatFoodBO> list = BeanCopierUtils.copyListProperties(eatFoodMapper.select(EatFoodDO.builder().userId(userId).build()), EatFoodBO.class);
        list.forEach(eatFoodBO -> {
            if (classifyNameMap.containsKey(eatFoodBO.getClassifyId())) {
                eatFoodBO.setClassifyName(classifyNameMap.get(eatFoodBO.getClassifyId()));
            }
        });
        return list;
    }

    @Override
    public void insert(EatFoodBO foodBO) {
        eatFoodMapper.insertSelective(BeanCopierUtils.copyProperties(foodBO, EatFoodDO.class));
    }

    @Override
    public void update(EatFoodBO foodBO) {
        eatFoodMapper.updateByPrimaryKey(BeanCopierUtils.copyProperties(foodBO, EatFoodDO.class));
    }

    @Override
    public void delete(Integer id) {
        eatFoodMapper.deleteByPrimaryKey(id);
    }
}
