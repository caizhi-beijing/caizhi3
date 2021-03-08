package com.caizhi.basics.service.impl;

import java.util.List;
import com.caizhi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caizhi.basics.mapper.TDataAcceptRequireMapper;
import com.caizhi.basics.domain.TDataAcceptRequire;
import com.caizhi.basics.domain.TDataAcceptRequireDTO;
import com.caizhi.basics.service.ITDataAcceptRequireService;

/**
 * 需求接收Service业务层处理
 * 
 * @author caizhi
 * @date 2021-03-04
 */
@Service
public class TDataAcceptRequireServiceImpl implements ITDataAcceptRequireService 
{
    @Autowired
    private TDataAcceptRequireMapper tDataAcceptRequireMapper;

    /**
     * 查询需求接收
     * 
     * @param id 需求接收ID
     * @return 需求接收
     */
    @Override
    public TDataAcceptRequireDTO selectTDataAcceptRequireById(Long id)
    {
        return tDataAcceptRequireMapper.selectTDataAcceptRequireById(id);
    }

    /**
     * 查询需求接收列表
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 需求接收
     */
    @Override
    public List<TDataAcceptRequire> selectTDataAcceptRequireList(TDataAcceptRequire tDataAcceptRequire)
    {
        return tDataAcceptRequireMapper.selectTDataAcceptRequireList(tDataAcceptRequire);
    }

    /**
     * 新增需求接收
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 结果
     */
    @Override
    public int insertTDataAcceptRequire(TDataAcceptRequire tDataAcceptRequire)
    {
        return tDataAcceptRequireMapper.insertTDataAcceptRequire(tDataAcceptRequire);
    }

    /**
     * 修改需求接收
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 结果
     */
    @Override
    public int updateTDataAcceptRequire(TDataAcceptRequire tDataAcceptRequire)
    {
        tDataAcceptRequire.setUpdateTime(DateUtils.getNowDate());
        return tDataAcceptRequireMapper.updateTDataAcceptRequire(tDataAcceptRequire);
    }

    /**
     * 批量删除需求接收
     * 
     * @param ids 需要删除的需求接收ID
     * @return 结果
     */
    @Override
    public int deleteTDataAcceptRequireByIds(Long[] ids)
    {
        return tDataAcceptRequireMapper.deleteTDataAcceptRequireByIds(ids);
    }

    /**
     * 删除需求接收信息
     * 
     * @param id 需求接收ID
     * @return 结果
     */
    @Override
    public int deleteTDataAcceptRequireById(Long id)
    {
        return tDataAcceptRequireMapper.deleteTDataAcceptRequireById(id);
    }

	@Override
	public List<TDataAcceptRequireDTO> selectAcceptOrRefuseRequire(String status) {
		
		return tDataAcceptRequireMapper.selectAcceptOrRefuseRequire(status);
	}

	@Override
	public List<TDataAcceptRequireDTO> selectCloseRequire() {
		
		return tDataAcceptRequireMapper.selectCloseRequire();
	}

	@Override
	public List<TDataAcceptRequireDTO> selectAllRequire() {
		
		return tDataAcceptRequireMapper.selectAllRequire();
	}

	@Override
	public int acceptOrRefuseRequire(TDataAcceptRequire tDataAcceptRequire) {
		return tDataAcceptRequireMapper.acceptOrRefuseRequire(tDataAcceptRequire);
	}
}
