package com.pighome.eat.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.pighome.eat.base.exception.EatException;
import com.pighome.eat.bo.EatFoodBO;
import com.pighome.eat.bo.EatRandomSettingBO;
import com.pighome.eat.dao.EatClassificationMapper;
import com.pighome.eat.dao.EatFoodMapper;
import com.pighome.eat.dao.EatRandomSettingMapper;
import com.pighome.eat.dataobject.EatClassificationDO;
import com.pighome.eat.dataobject.EatFoodDO;
import com.pighome.eat.dataobject.EatRandomSettingDO;
import com.pighome.eat.service.RandomService;
import com.pighome.eat.utils.BeanCopierUtils;
import com.pighome.eat.utils.StreamUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author zoo
 * @date 2021/1/21
 */
@Service
public class RandomServiceImpl implements RandomService {

    @Autowired
    EatRandomSettingMapper eatRandomSettingMapper;
    @Autowired
    EatFoodMapper eatFoodMapper;
    @Autowired
    EatClassificationMapper eatClassificationMapper;

    @Override
    public List<EatRandomSettingBO> getList(String userId) {
        return BeanCopierUtils.copyListProperties(eatRandomSettingMapper.select(EatRandomSettingDO.builder().userId(userId).build()), EatRandomSettingBO.class);
    }

    @Override
    public void insert(EatRandomSettingBO settingBO) {
        eatRandomSettingMapper.insertSelective(BeanCopierUtils.copyProperties(settingBO, EatRandomSettingDO.class));
    }

    @Override
    public void update(EatRandomSettingBO settingBO) {
        eatRandomSettingMapper.updateByPrimaryKey(BeanCopierUtils.copyProperties(settingBO, EatRandomSettingDO.class));
    }

    @Override
    public void delete(Integer id) {
        eatRandomSettingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EatFoodBO> randomGet(String userId) {
        // 获取设置
        List<EatRandomSettingDO> setting = eatRandomSettingMapper.select(EatRandomSettingDO.builder().userId(userId).build());
        if (CollUtil.isEmpty(setting)) {
            throw new EatException("请先进行随机设置");
        }
        // 获取对应分类的食物
        Example foodSelect = Example.builder(EatFoodDO.class).build();
        foodSelect.createCriteria().andIn("classifyId", setting.stream().map(EatRandomSettingDO::getClassifyId).collect(Collectors.toList()));
        Map<Integer, List<EatFoodDO>> foodClassifyMap = StreamUtil.ListToMapList(eatFoodMapper.selectByExample(foodSelect), EatFoodDO::getClassifyId);
        Example classifySelect = Example.builder(EatClassificationDO.class).build();
        foodSelect.createCriteria().andIn("id", setting.stream().map(EatRandomSettingDO::getClassifyId).collect(Collectors.toList()));
        Map<Integer, EatClassificationDO> classifyNameMap = StreamUtil.listToMap(eatClassificationMapper.selectByExample(classifySelect), EatClassificationDO::getId);
        classifyNameMap.put(0, EatClassificationDO.builder().id(0).name("默认").userId(userId).remark("系统默认").build());
        List<EatFoodBO> resList = Lists.newArrayList();
        setting.forEach(eatRandomSettingDO -> {
            if (!classifyNameMap.containsKey(eatRandomSettingDO.getClassifyId())) {
                return;
            }
            List<EatFoodDO> foodDOList = foodClassifyMap.getOrDefault(eatRandomSettingDO.getClassifyId(), Lists.newArrayList());
            Collections.shuffle(foodDOList);
            resList.addAll(foodDOList.subList(0, Math.min(eatRandomSettingDO.getRandomCount(), foodDOList.size())).stream().map(eatFoodDO -> {
                EatFoodBO eatFoodBO = BeanCopierUtils.copyProperties(eatFoodDO, EatFoodBO.class);
                eatFoodBO.setClassifyName(classifyNameMap.get(eatRandomSettingDO.getClassifyId()).getName());
                return eatFoodBO;
            }).collect(Collectors.toList()));
        });
        return resList;
    }
}
