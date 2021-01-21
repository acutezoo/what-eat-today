package com.pighome.eat.service.impl;

import com.pighome.eat.bo.EatClassificationBO;
import com.pighome.eat.dao.EatClassificationMapper;
import com.pighome.eat.dao.EatFoodMapper;
import com.pighome.eat.dataobject.EatClassificationDO;
import com.pighome.eat.dataobject.EatFoodDO;
import com.pighome.eat.service.ClassifyService;
import com.pighome.eat.utils.BeanCopierUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zoo
 * @date 2021/1/21
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    EatClassificationMapper eatClassificationMapper;
    @Autowired
    EatFoodMapper eatFoodMapper;

    @Override
    public List<EatClassificationBO> getList(String userId) {
        List<EatClassificationBO> list = BeanCopierUtils
                .copyListProperties(eatClassificationMapper.select(EatClassificationDO.builder().userId(userId).build()), EatClassificationBO.class);
        list.add(EatClassificationBO.builder().id(0).name("默认").userId(userId).remark("系统默认").build());
        return list;
    }

    @Override
    public void insert(EatClassificationBO eatClassificationBO) {
        eatClassificationMapper.insertSelective(BeanCopierUtils.copyProperties(eatClassificationBO, EatClassificationDO.class));
    }

    @Override
    public void update(EatClassificationBO eatClassificationBO) {
        eatClassificationMapper.updateByPrimaryKeySelective(BeanCopierUtils.copyProperties(eatClassificationBO, EatClassificationDO.class));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // 删除明细
        eatFoodMapper.delete(EatFoodDO.builder().classifyId(id).build());
        // 删除分类
        eatClassificationMapper.deleteByPrimaryKey(id);
    }
}
