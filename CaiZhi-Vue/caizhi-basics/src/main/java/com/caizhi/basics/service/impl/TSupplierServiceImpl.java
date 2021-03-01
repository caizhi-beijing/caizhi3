package com.caizhi.basics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caizhi.basics.mapper.TSupplierMapper;
import com.caizhi.basics.domain.TSupplier;
import com.caizhi.basics.service.ITSupplierService;

/**
 * 供应商信息Service业务层处理
 * 
 * @author caizhi
 * @date 2021-03-01
 */
@Service
public class TSupplierServiceImpl implements ITSupplierService 
{
    @Autowired
    private TSupplierMapper tSupplierMapper;

    /**
     * 查询供应商信息
     * 
     * @param id 供应商信息ID
     * @return 供应商信息
     */
    @Override
    public TSupplier selectTSupplierById(Long id)
    {
        return tSupplierMapper.selectTSupplierById(id);
    }

    /**
     * 查询供应商信息列表
     * 
     * @param tSupplier 供应商信息
     * @return 供应商信息
     */
    @Override
    public List<TSupplier> selectTSupplierList(TSupplier tSupplier)
    {
        return tSupplierMapper.selectTSupplierList(tSupplier);
    }

    /**
     * 新增供应商信息
     * 
     * @param tSupplier 供应商信息
     * @return 结果
     */
    @Override
    public int insertTSupplier(TSupplier tSupplier)
    {
        return tSupplierMapper.insertTSupplier(tSupplier);
    }

    /**
     * 修改供应商信息
     * 
     * @param tSupplier 供应商信息
     * @return 结果
     */
    @Override
    public int updateTSupplier(TSupplier tSupplier)
    {
        return tSupplierMapper.updateTSupplier(tSupplier);
    }

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的供应商信息ID
     * @return 结果
     */
    @Override
    public int deleteTSupplierByIds(Long[] ids)
    {
        return tSupplierMapper.deleteTSupplierByIds(ids);
    }

    /**
     * 删除供应商信息信息
     * 
     * @param id 供应商信息ID
     * @return 结果
     */
    @Override
    public int deleteTSupplierById(Long id)
    {
        return tSupplierMapper.deleteTSupplierById(id);
    }
}
