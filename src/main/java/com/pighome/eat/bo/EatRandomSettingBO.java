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
public class EatRandomSettingBO {

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
     * 随机选择数量
     */
    private Integer randomCount;


}