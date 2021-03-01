package com.caizhi.basics.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.caizhi.common.annotation.Excel;
import com.caizhi.common.core.domain.BaseEntity;

/**
 * 供应商信息对象 t_data_supplier
 * 
 * @author caizhi
 * @date 2021-03-01
 */
public class TSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String creditCode;

    /** 供应商注册地址 */
    @Excel(name = "供应商注册地址")
    private String address;

    /** 供应商注册资金 */
    @Excel(name = "供应商注册资金")
    private BigDecimal capital;

    /** 供应商来源 */
    @Excel(name = "供应商来源")
    private String source;

    /** 供应商等级 */
    @Excel(name = "供应商等级")
    private String level;

    /** 供应商联系人手机号 */
    @Excel(name = "供应商联系人手机号")
    private Long phone;

    /** 供应商联系邮箱 */
    @Excel(name = "供应商联系邮箱")
    private String email;

    /** 框架协议起始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "框架协议起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 框架协议终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "框架协议终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 供应商状态 */
    @Excel(name = "供应商状态")
    private String status;

    /** 供应商资质文件 */
    @Excel(name = "供应商资质文件")
    private String qualificationsFile;

    /** 供应商营业执照 */
    @Excel(name = "供应商营业执照")
    private String businessLicense;

    /** 供应商银行开户证明 */
    @Excel(name = "供应商银行开户证明")
    private String accountOpeningCertificate;

    /** 公共邮箱地址 */
    @Excel(name = "公共邮箱地址")
    private String publicMailbox;

    /** 供应商联系人 */
    @Excel(name = "供应商联系人")
    private String name;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setCreditCode(String creditCode) 
    {
        this.creditCode = creditCode;
    }

    public String getCreditCode() 
    {
        return creditCode;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCapital(BigDecimal capital) 
    {
        this.capital = capital;
    }

    public BigDecimal getCapital() 
    {
        return capital;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setPhone(Long phone) 
    {
        this.phone = phone;
    }

    public Long getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setQualificationsFile(String qualificationsFile) 
    {
        this.qualificationsFile = qualificationsFile;
    }

    public String getQualificationsFile() 
    {
        return qualificationsFile;
    }
    public void setBusinessLicense(String businessLicense) 
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() 
    {
        return businessLicense;
    }
    public void setAccountOpeningCertificate(String accountOpeningCertificate) 
    {
        this.accountOpeningCertificate = accountOpeningCertificate;
    }

    public String getAccountOpeningCertificate() 
    {
        return accountOpeningCertificate;
    }
    public void setPublicMailbox(String publicMailbox) 
    {
        this.publicMailbox = publicMailbox;
    }

    public String getPublicMailbox() 
    {
        return publicMailbox;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("supplierName", getSupplierName())
            .append("creditCode", getCreditCode())
            .append("address", getAddress())
            .append("capital", getCapital())
            .append("source", getSource())
            .append("level", getLevel())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("qualificationsFile", getQualificationsFile())
            .append("businessLicense", getBusinessLicense())
            .append("accountOpeningCertificate", getAccountOpeningCertificate())
            .append("publicMailbox", getPublicMailbox())
            .append("name", getName())
            .toString();
    }
}
