package com.caizhi.basics.service;

import java.util.List;
import com.caizhi.basics.domain.TDataAcceptRequire;
import com.caizhi.basics.domain.TDataAcceptRequireDTO;

/**
 * 需求接收Service接口
 * 
 * @author caizhi
 * @date 2021-03-04
 */
public interface ITDataAcceptRequireService 
{
    /**
     * 查询需求接收
     * 
     * @param id 需求接收ID
     * @return 需求接收
     */
    public TDataAcceptRequireDTO selectTDataAcceptRequireById(Long id);

    /**
     * 查询需求接收列表
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 需求接收集合
     */
    public List<TDataAcceptRequire> selectTDataAcceptRequireList(TDataAcceptRequire tDataAcceptRequire);

    /**
     * 新增需求接收
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 结果
     */
    public int insertTDataAcceptRequire(TDataAcceptRequire tDataAcceptRequire);

    /**
     * 修改需求接收
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 结果
     */
    public int updateTDataAcceptRequire(TDataAcceptRequire tDataAcceptRequire);

    /**
     * 批量删除需求接收
     * 
     * @param ids 需要删除的需求接收ID
     * @return 结果
     */
    public int deleteTDataAcceptRequireByIds(Long[] ids);

    /**
     * 删除需求接收信息
     * 
     * @param id 需求接收ID
     * @return 结果
     */
    public int deleteTDataAcceptRequireById(Long id);
    
    /**
     * 查询待接收，已接受，已拒收的需求 
     * 
     * @param status 状态
     * @return 需求接收集合
     */
    public List<TDataAcceptRequireDTO> selectAcceptOrRefuseRequire(String status);
    
    /**
     * 查询已关闭的需求
     * 
     * @return 需求接收集合
     */
    public List<TDataAcceptRequireDTO> selectCloseRequire();
    
    /**
     * 查询所有的需求 
     * 
     * @return 需求接收集合
     */
    public List<TDataAcceptRequireDTO> selectAllRequire();
    
    /**
     * 接收或者拒绝需求
     * 
     * @param tDataAcceptRequire 需求接收
     * @return 结果
     */
    public int acceptOrRefuseRequire(TDataAcceptRequire tDataAcceptRequire);
}
