package com.caizhi.basics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.caizhi.common.annotation.Excel;
import com.caizhi.common.core.domain.BaseEntity;

/**
 * 候选人信息对象 t_data_candidate_information
 * 
 * @author caizhi
 * @date 2021-03-04
 */
public class TDataCandidateInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 候选人姓名 */
    @Excel(name = "候选人姓名")
    private String name;

    /** 候选人邮箱 */
    @Excel(name = "候选人邮箱")
    private String email;

    /** 候选人电话 */
    @Excel(name = "候选人电话")
    private String phone;

    /** 推荐人评语 */
    @Excel(name = "推荐人评语")
    private String comment;

    /** 简历 */
    @Excel(name = "简历")
    private String resume;

    /** 附件 */
    @Excel(name = "附件")
    private String enclosure;

    /** excle文件 */
    @Excel(name = "excle文件")
    private String excelFile;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setResume(String resume) 
    {
        this.resume = resume;
    }

    public String getResume() 
    {
        return resume;
    }
    public void setEnclosure(String enclosure) 
    {
        this.enclosure = enclosure;
    }

    public String getEnclosure() 
    {
        return enclosure;
    }
    public void setExcelFile(String excelFile) 
    {
        this.excelFile = excelFile;
    }

    public String getExcelFile() 
    {
        return excelFile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("comment", getComment())
            .append("resume", getResume())
            .append("enclosure", getEnclosure())
            .append("excelFile", getExcelFile())
            .toString();
    }
}
