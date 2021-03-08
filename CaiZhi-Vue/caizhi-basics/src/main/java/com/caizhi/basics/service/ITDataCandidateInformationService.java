package com.caizhi.basics.service;

import java.util.List;
import com.caizhi.basics.domain.TDataCandidateInformation;

/**
 * 候选人信息Service接口
 * 
 * @author caizhi
 * @date 2021-03-04
 */
public interface ITDataCandidateInformationService 
{
    /**
     * 查询候选人信息
     * 
     * @param id 候选人信息ID
     * @return 候选人信息
     */
    public TDataCandidateInformation selectTDataCandidateInformationById(Long id);

    /**
     * 查询候选人信息列表
     * 
     * @param tDataCandidateInformation 候选人信息
     * @return 候选人信息集合
     */
    public List<TDataCandidateInformation> selectTDataCandidateInformationList(TDataCandidateInformation tDataCandidateInformation);

    /**
     * 新增候选人信息
     * 
     * @param tDataCandidateInformation 候选人信息
     * @return 结果
     */
    public int insertTDataCandidateInformation(TDataCandidateInformation tDataCandidateInformation);

    /**
     * 修改候选人信息
     * 
     * @param tDataCandidateInformation 候选人信息
     * @return 结果
     */
    public int updateTDataCandidateInformation(TDataCandidateInformation tDataCandidateInformation);

    /**
     * 批量删除候选人信息
     * 
     * @param ids 需要删除的候选人信息ID
     * @return 结果
     */
    public int deleteTDataCandidateInformationByIds(Long[] ids);

    /**
     * 删除候选人信息信息
     * 
     * @param id 候选人信息ID
     * @return 结果
     */
    public int deleteTDataCandidateInformationById(Long id);
    
    /**
     * 根据name和 phone查询候选人信息
     * 
     * @param name 候选人名字
     * @param phone 候选人电话
     * @return 候选人信息
     */
    public TDataCandidateInformation getCandidateIfExist(String name,String phone);
    
}
