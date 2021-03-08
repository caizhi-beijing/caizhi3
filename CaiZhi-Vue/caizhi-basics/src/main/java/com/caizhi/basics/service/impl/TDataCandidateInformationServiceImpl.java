package com.caizhi.basics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caizhi.basics.mapper.TDataCandidateInformationMapper;
import com.caizhi.basics.domain.TDataCandidateInformation;
import com.caizhi.basics.service.ITDataCandidateInformationService;

/**
 * 候选人信息Service业务层处理
 * 
 * @author caizhi
 * @date 2021-03-04
 */
@Service
public class TDataCandidateInformationServiceImpl implements ITDataCandidateInformationService 
{
    @Autowired
    private TDataCandidateInformationMapper tDataCandidateInformationMapper;

    /**
     * 查询候选人信息
     * 
     * @param id 候选人信息ID
     * @return 候选人信息
     */
    @Override
    public TDataCandidateInformation selectTDataCandidateInformationById(Long id)
    {
        return tDataCandidateInformationMapper.selectTDataCandidateInformationById(id);
    }

    /**
     * 查询候选人信息列表
     * 
     * @param tDataCandidateInformation 候选人信息
     * @return 候选人信息
     */
    @Override
    public List<TDataCandidateInformation> selectTDataCandidateInformationList(TDataCandidateInformation tDataCandidateInformation)
    {
        return tDataCandidateInformationMapper.selectTDataCandidateInformationList(tDataCandidateInformation);
    }

    /**
     * 新增候选人信息
     * 
     * @param tDataCandidateInformation 候选人信息
     * @return 结果
     */
    @Override
    public int insertTDataCandidateInformation(TDataCandidateInformation tDataCandidateInformation)
    {
        return tDataCandidateInformationMapper.insertTDataCandidateInformation(tDataCandidateInformation);
    }

    /**
     * 修改候选人信息
     * 
     * @param tDataCandidateInformation 候选人信息
     * @return 结果
     */
    @Override
    public int updateTDataCandidateInformation(TDataCandidateInformation tDataCandidateInformation)
    {
        return tDataCandidateInformationMapper.updateTDataCandidateInformation(tDataCandidateInformation);
    }

    /**
     * 批量删除候选人信息
     * 
     * @param ids 需要删除的候选人信息ID
     * @return 结果
     */
    @Override
    public int deleteTDataCandidateInformationByIds(Long[] ids)
    {
        return tDataCandidateInformationMapper.deleteTDataCandidateInformationByIds(ids);
    }

    /**
     * 删除候选人信息信息
     * 
     * @param id 候选人信息ID
     * @return 结果
     */
    @Override
    public int deleteTDataCandidateInformationById(Long id)
    {
        return tDataCandidateInformationMapper.deleteTDataCandidateInformationById(id);
    }

    /**
     * 根据name和 phone查询候选人信息
     * 
     * @param name 候选人名字
     * @param phone 候选人电话
     * @return 候选人信息
     */
	@Override
	public TDataCandidateInformation getCandidateIfExist(String name, String phone) {
		TDataCandidateInformation tDataCandidateInformation = new TDataCandidateInformation();
		tDataCandidateInformation.setName(name);
		tDataCandidateInformation.setPhone(phone);
		tDataCandidateInformationMapper.selectCandidateIfExist(tDataCandidateInformation);
		return null;
	}
}
