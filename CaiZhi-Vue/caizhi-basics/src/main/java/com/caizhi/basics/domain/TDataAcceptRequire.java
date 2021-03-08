package com.caizhi.basics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.caizhi.common.annotation.Excel;
import com.caizhi.common.core.domain.BaseEntity;

/**
 * 需求接收对象 t_data_accept_require
 * 
 * @author caizhi
 * @date 2021-03-04
 */
public class TDataAcceptRequire extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 需求id */
    @Excel(name = "需求id")
    private Long requireId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRequireId(Long requireId) 
    {
        this.requireId = requireId;
    }

    public Long getRequireId() 
    {
        return requireId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("requireId", getRequireId())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
