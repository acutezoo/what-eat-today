package com.pighome.eat.controller;

import cn.hutool.core.util.StrUtil;
import com.pighome.eat.base.exception.EatException;
import com.pighome.eat.base.response.RestResponse;
import com.pighome.eat.bo.EatFoodBO;
import com.pighome.eat.bo.EatRandomSettingBO;
import com.pighome.eat.service.RandomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/eat/random")
public class EatRandomController {

    @Autowired
    RandomService randomService;

    @GetMapping("/setting/getList.do")
    public RestResponse<List<EatRandomSettingBO>> getList(String userId) {
        if (StrUtil.isBlank(userId)) {
            throw new EatException("参数错误，userId为空");
        }
        return RestResponse.success(randomService.getList(userId));
    }

    @PostMapping("/setting/add.do")
    public RestResponse add(@RequestBody EatRandomSettingBO bo) {
        randomService.insert(bo);
        return RestResponse.success();
    }

    @PostMapping("/setting/update.do")
    public RestResponse update(@RequestBody EatRandomSettingBO bo) {
        if (StrUtil.isBlank(bo.getUserId())) {
            throw new EatException("参数错误，userId为空");
        }
        randomService.update(bo);
        return RestResponse.success();
    }

    @PostMapping("/setting/delete.do")
    public RestResponse delete(Integer id) {
        randomService.delete(id);
        return RestResponse.success();
    }

    @GetMapping("/random.do")
    public RestResponse<List<EatFoodBO>> random(String userId) {
        if (StrUtil.isBlank(userId)) {
            throw new EatException("参数错误，userId为空");
        }
        return RestResponse.success(randomService.randomGet(userId));
    }

}