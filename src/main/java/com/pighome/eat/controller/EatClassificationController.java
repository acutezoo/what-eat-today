package com.pighome.eat.controller;

import cn.hutool.core.util.StrUtil;
import com.pighome.eat.base.exception.EatException;
import com.pighome.eat.base.response.RestResponse;
import com.pighome.eat.bo.EatClassificationBO;
import com.pighome.eat.service.ClassifyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zoo
 * @date 2021/1/21
 */
@RestController
@RequestMapping(value = "/eat/classify")
public class EatClassificationController {

    @Autowired
    ClassifyService classifyService;

    @GetMapping("/getList.do")
    public RestResponse<List<EatClassificationBO>> getList(String userId) {
        if (StrUtil.isBlank(userId)) {
            throw new EatException("参数错误，userId为空");
        }
        return RestResponse.success(classifyService.getList(userId));
    }

    @PostMapping("/add.do")
    public RestResponse add(@RequestBody EatClassificationBO bo) {
        classifyService.insert(bo);
        return RestResponse.success();
    }

    @PostMapping("/update.do")
    public RestResponse update(@RequestBody EatClassificationBO bo) {
        if (StrUtil.isBlank(bo.getUserId())) {
            throw new EatException("参数错误，userId为空");
        }
        classifyService.update(bo);
        return RestResponse.success();
    }

    @PostMapping("/delete.do")
    public RestResponse delete(Integer id) {
        classifyService.delete(id);
        return RestResponse.success();
    }
}
