package com.caizhi.basics.service;

import java.util.List;
import com.caizhi.basics.domain.TDataDemanDapplication;

/**
 * 需求审批Service接口
 * 
 * @author caizhi
 * @date 2021-02-05
 */
public interface ITDataDemanDapplicationService 
{
    /**
     * 查询需求审批
     * 
     * @param id 需求审批ID
     * @return 需求审批
     */
    public TDataDemanDapplication selectTDataDemanDapplicationById(Long id);

    /**
     * 查询需求审批列表
     * 
     * @param tDataDemanDapplication 需求审批
     * @return 需求审批集合
     */
    public List<TDataDemanDapplication> selectTDataDemanDapplicationList(TDataDemanDapplication tDataDemanDapplication);

    /**
     * 新增需求审批
     * 
     * @param tDataDemanDapplication 需求审批
     * @return 结果
     */
    public int insertTDataDemanDapplication(TDataDemanDapplication tDataDemanDapplication);

    /**
     * 修改需求审批
     * 
     * @param tDataDemanDapplication 需求审批
     * @return 结果
     */
    public int updateTDataDemanDapplication(TDataDemanDapplication tDataDemanDapplication);

    /**
     * 批量删除需求审批
     * 
     * @param ids 需要删除的需求审批ID
     * @return 结果
     */
    public int deleteTDataDemanDapplicationByIds(Long[] ids);

    /**
     * 删除需求审批信息
     * 
     * @param id 需求审批ID
     * @return 结果
     */
    public int deleteTDataDemanDapplicationById(Long id);
}
