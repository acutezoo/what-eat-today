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
public class EatClassificationBO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 登录人id
     */
    private String userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

}