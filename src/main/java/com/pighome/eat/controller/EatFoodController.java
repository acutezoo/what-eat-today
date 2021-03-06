package com.pighome.eat.controller;

import cn.hutool.core.util.StrUtil;
import com.pighome.eat.base.exception.EatException;
import com.pighome.eat.base.response.RestResponse;
import com.pighome.eat.bo.EatFoodBO;
import com.pighome.eat.service.FoodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/eat/food")
public class EatFoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/getList.do")
    public RestResponse<List<EatFoodBO>> getList(String userId) {
        if (StrUtil.isBlank(userId)) {
            throw new EatException("参数错误，userId为空");
        }
        return RestResponse.success(foodService.getList(userId));
    }

    @PostMapping("/add.do")
    public RestResponse add(@RequestBody EatFoodBO bo) {
        foodService.insert(bo);
        return RestResponse.success();
    }

    @PostMapping("/update.do")
    public RestResponse update(@RequestBody EatFoodBO bo) {
        if (StrUtil.isBlank(bo.getUserId())) {
            throw new EatException("参数错误，userId为空");
        }
        foodService.update(bo);
        return RestResponse.success();
    }

    @PostMapping("/delete.do")
    public RestResponse delete(Integer id) {
        foodService.delete(id);
        return RestResponse.success();
    }
}