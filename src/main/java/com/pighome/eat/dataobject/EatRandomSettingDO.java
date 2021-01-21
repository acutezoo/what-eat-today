package com.pighome.eat.dataobject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "eat_random_setting")
public class EatRandomSettingDO {

    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 登录人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 分类id;0=默认
     */
    @Column(name = "classify_id")
    private Integer classifyId;

    /**
     * 随机选择数量
     */
    @Column(name = "random_count")
    private Integer randomCount;

    /**
     * 逻辑删除id
     */
    @Column(name = "delete_id")
    private Integer deleteId;

    /**
     * 新增时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间(数据库自动维护)
     */
    @Column(name = "modify_time")
    private Date modifyTime;

}