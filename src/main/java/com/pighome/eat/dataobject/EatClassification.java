package com.pighome.eat.dataobject;

import java.util.Date;
import javax.persistence.*;

@Table(name = "eat_classification")
public class EatClassification {
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

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录人id
     *
     * @return user_id - 登录人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置登录人id
     *
     * @param userId 登录人id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取逻辑删除id
     *
     * @return delete_id - 逻辑删除id
     */
    public Integer getDeleteId() {
        return deleteId;
    }

    /**
     * 设置逻辑删除id
     *
     * @param deleteId 逻辑删除id
     */
    public void setDeleteId(Integer deleteId) {
        this.deleteId = deleteId;
    }

    /**
     * 获取新增时间
     *
     * @return create_time - 新增时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置新增时间
     *
     * @param createTime 新增时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间(数据库自动维护)
     *
     * @return modify_time - 修改时间(数据库自动维护)
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间(数据库自动维护)
     *
     * @param modifyTime 修改时间(数据库自动维护)
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}