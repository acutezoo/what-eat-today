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
@Table(name = "eat_classification")
public class EatClassificationDO {

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
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

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