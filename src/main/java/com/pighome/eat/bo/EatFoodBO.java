package com.pighome.eat.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EatFoodBO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 登录人id
     */
    private String userId;

    /**
     * 分类id;0=默认
     */
    private Integer classifyId;

    /**
     * 分类名称
     */
    private String classifyName;


    /**
     * 食物名称
     */
    private String foodName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图片地址
     */
    private String imgUrl;


}