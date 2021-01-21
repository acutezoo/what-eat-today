package com.pighome.eat.dataobject;

import java.util.Date;
import javax.persistence.*;

@Table(name = "eat_food")
public class EatFood {
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
     * 食物名称
     */
    @Column(name = "food_name")
    private String foodName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;

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
     * 获取分类id;0=默认
     *
     * @return classify_id - 分类id;0=默认
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * 设置分类id;0=默认
     *
     * @param classifyId 分类id;0=默认
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * 获取食物名称
     *
     * @return food_name - 食物名称
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * 设置食物名称
     *
     * @param foodName 食物名称
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
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
     * 获取图片地址
     *
     * @return img_url - 图片地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imgUrl 图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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