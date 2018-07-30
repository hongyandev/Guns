package com.stylefeng.guns.modular.custom.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 问题表
 * </p>
 *
 * @author guanqing123
 * @since 2018-07-30
 */
@TableName("app_faq")
public class Faq extends Model<Faq> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 问题类别
     */
    private Integer faqType;
    /**
     * 问题
     */
    private String faqTitle;
    /**
     * 回答
     */
    private String faqContent;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFaqType() {
        return faqType;
    }

    public void setFaqType(Integer faqType) {
        this.faqType = faqType;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqContent() {
        return faqContent;
    }

    public void setFaqContent(String faqContent) {
        this.faqContent = faqContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Faq{" +
        "id=" + id +
        ", faqType=" + faqType +
        ", faqTitle=" + faqTitle +
        ", faqContent=" + faqContent +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
