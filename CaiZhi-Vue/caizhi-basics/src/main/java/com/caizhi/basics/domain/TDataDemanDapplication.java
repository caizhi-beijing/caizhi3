package com.caizhi.basics.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.caizhi.common.annotation.Excel;
import com.caizhi.common.core.domain.BaseEntity;

/**
 * 需求审批对象 t_data_deman_dapplication
 * 
 * @author caizhi
 * @date 2021-03-04
 */
public class TDataDemanDapplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 需求编号 */
    @Excel(name = "需求编号")
    private String requireNumber;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 岗位类别 */
    @Excel(name = "岗位类别")
    private String postType;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String postName;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 数量 */
    @Excel(name = "数量")
    private Long amount;

    /** 紧急程度 */
    @Excel(name = "紧急程度")
    private String urgency;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    /** 供应商范围 */
    @Excel(name = "供应商范围")
    private String supplierScope;

    /** 面试官 */
    @Excel(name = "面试官")
    private String interviewer;

    /** 计划进场时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划进场时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedAppravlTime;

    /** 供应商范围id */
    @Excel(name = "供应商范围id")
    private String supplierScopeId;

    /** 工作说明 */
    @Excel(name = "工作说明")
    private String jobDesc;

    /** 任职要求 */
    @Excel(name = "任职要求")
    private String jobRequired;

    /** 计划使用周期 */
    @Excel(name = "计划使用周期")
    private String plannedLifeCycle;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applicant;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applicationTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 发布时间选项（1立即发布，2选择发布时间） */
    @Excel(name = "发布时间选项", readConverterExp = "1=立即发布，2选择发布时间")
    private String releaseTimeFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRequireNumber(String requireNumber) 
    {
        this.requireNumber = requireNumber;
    }

    public String getRequireNumber() 
    {
        return requireNumber;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setPostType(String postType) 
    {
        this.postType = postType;
    }

    public String getPostType() 
    {
        return postType;
    }
    public void setPostName(String postName) 
    {
        this.postName = postName;
    }

    public String getPostName() 
    {
        return postName;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setUrgency(String urgency) 
    {
        this.urgency = urgency;
    }

    public String getUrgency() 
    {
        return urgency;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setSupplierScope(String supplierScope) 
    {
        this.supplierScope = supplierScope;
    }

    public String getSupplierScope() 
    {
        return supplierScope;
    }
    public void setInterviewer(String interviewer) 
    {
        this.interviewer = interviewer;
    }

    public String getInterviewer() 
    {
        return interviewer;
    }
    public void setPlannedAppravlTime(Date plannedAppravlTime) 
    {
        this.plannedAppravlTime = plannedAppravlTime;
    }

    public Date getPlannedAppravlTime() 
    {
        return plannedAppravlTime;
    }
    public void setSupplierScopeId(String supplierScopeId) 
    {
        this.supplierScopeId = supplierScopeId;
    }

    public String getSupplierScopeId() 
    {
        return supplierScopeId;
    }
    public void setJobDesc(String jobDesc) 
    {
        this.jobDesc = jobDesc;
    }

    public String getJobDesc() 
    {
        return jobDesc;
    }
    public void setJobRequired(String jobRequired) 
    {
        this.jobRequired = jobRequired;
    }

    public String getJobRequired() 
    {
        return jobRequired;
    }
    public void setPlannedLifeCycle(String plannedLifeCycle) 
    {
        this.plannedLifeCycle = plannedLifeCycle;
    }

    public String getPlannedLifeCycle() 
    {
        return plannedLifeCycle;
    }
    public void setApplicant(String applicant) 
    {
        this.applicant = applicant;
    }

    public String getApplicant() 
    {
        return applicant;
    }
    public void setApplicationTime(Date applicationTime) 
    {
        this.applicationTime = applicationTime;
    }

    public Date getApplicationTime() 
    {
        return applicationTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setReleaseTime(Date releaseTime) 
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime() 
    {
        return releaseTime;
    }
    public void setReleaseTimeFlag(String releaseTimeFlag) 
    {
        this.releaseTimeFlag = releaseTimeFlag;
    }

    public String getReleaseTimeFlag() 
    {
        return releaseTimeFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("requireNumber", getRequireNumber())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("projectName", getProjectName())
            .append("postType", getPostType())
            .append("postName", getPostName())
            .append("level", getLevel())
            .append("amount", getAmount())
            .append("urgency", getUrgency())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("supplierScope", getSupplierScope())
            .append("interviewer", getInterviewer())
            .append("plannedAppravlTime", getPlannedAppravlTime())
            .append("supplierScopeId", getSupplierScopeId())
            .append("jobDesc", getJobDesc())
            .append("jobRequired", getJobRequired())
            .append("plannedLifeCycle", getPlannedLifeCycle())
            .append("applicant", getApplicant())
            .append("applicationTime", getApplicationTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("releaseTime", getReleaseTime())
            .append("releaseTimeFlag", getReleaseTimeFlag())
            .toString();
    }
}
