package com.caizhi.basics.mapper;

import java.util.List;
import com.caizhi.basics.domain.TSupplier;

/**
 * 供应商信息Mapper接口
 * 
 * @author caizhi
 * @date 2021-03-01
 */
public interface TSupplierMapper 
{
    /**
     * 查询供应商信息
     * 
     * @param id 供应商信息ID
     * @return 供应商信息
     */
    public TSupplier selectTSupplierById(Long id);

    /**
     * 查询供应商信息列表
     * 
     * @param tSupplier 供应商信息
     * @return 供应商信息集合
     */
    public List<TSupplier> selectTSupplierList(TSupplier tSupplier);

    /**
     * 新增供应商信息
     * 
     * @param tSupplier 供应商信息
     * @return 结果
     */
    public int insertTSupplier(TSupplier tSupplier);

    /**
     * 修改供应商信息
     * 
     * @param tSupplier 供应商信息
     * @return 结果
     */
    public int updateTSupplier(TSupplier tSupplier);

    /**
     * 删除供应商信息
     * 
     * @param id 供应商信息ID
     * @return 结果
     */
    public int deleteTSupplierById(Long id);

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSupplierByIds(Long[] ids);
}
