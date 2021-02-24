package com.caizhi.basics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caizhi.basics.mapper.TDataDemanDapplicationMapper;
import com.caizhi.basics.domain.TDataDemanDapplication;
import com.caizhi.basics.service.ITDataDemanDapplicationService;

/**
 * 需求审批Service业务层处理
 * 
 * @author caizhi
 * @date 2021-02-05
 */
@Service
public class TDataDemanDapplicationServiceImpl implements ITDataDemanDapplicationService 
{
    @Autowired
    private TDataDemanDapplicationMapper tDataDemanDapplicationMapper;

    /**
     * 查询需求审批
     * 
     * @param id 需求审批ID
     * @return 需求审批
     */
    @Override
    public TDataDemanDapplication selectTDataDemanDapplicationById(Long id)
    {
        return tDataDemanDapplicationMapper.selectTDataDemanDapplicationById(id);
    }

    /**
     * 查询需求审批列表
     * 
     * @param tDataDemanDapplication 需求审批
     * @return 需求审批
     */
    @Override
    public List<TDataDemanDapplication> selectTDataDemanDapplicationList(TDataDemanDapplication tDataDemanDapplication)
    {
        return tDataDemanDapplicationMapper.selectTDataDemanDapplicationList(tDataDemanDapplication);
    }

    /**
     * 新增需求审批
     * 
     * @param tDataDemanDapplication 需求审批
     * @return 结果
     */
    @Override
    public int insertTDataDemanDapplication(TDataDemanDapplication tDataDemanDapplication)
    {
        return tDataDemanDapplicationMapper.insertTDataDemanDapplication(tDataDemanDapplication);
    }

    /**
     * 修改需求审批
     * 
     * @param tDataDemanDapplication 需求审批
     * @return 结果
     */
    @Override
    public int updateTDataDemanDapplication(TDataDemanDapplication tDataDemanDapplication)
    {
        return tDataDemanDapplicationMapper.updateTDataDemanDapplication(tDataDemanDapplication);
    }

    /**
     * 批量删除需求审批
     * 
     * @param ids 需要删除的需求审批ID
     * @return 结果
     */
    @Override
    public int deleteTDataDemanDapplicationByIds(Long[] ids)
    {
        return tDataDemanDapplicationMapper.deleteTDataDemanDapplicationByIds(ids);
    }

    /**
     * 删除需求审批信息
     * 
     * @param id 需求审批ID
     * @return 结果
     */
    @Override
    public int deleteTDataDemanDapplicationById(Long id)
    {
        return tDataDemanDapplicationMapper.deleteTDataDemanDapplicationById(id);
    }
}
