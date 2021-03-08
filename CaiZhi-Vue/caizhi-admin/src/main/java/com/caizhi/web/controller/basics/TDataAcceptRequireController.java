package com.caizhi.web.controller.basics;

import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.caizhi.common.annotation.Log;
import com.caizhi.common.constant.AcceptRequireConstants;
import com.caizhi.common.core.controller.BaseController;
import com.caizhi.common.core.domain.AjaxResult;
import com.caizhi.common.enums.BusinessType;
import com.caizhi.basics.domain.TDataAcceptRequire;
import com.caizhi.basics.domain.TDataAcceptRequireDTO;
import com.caizhi.basics.service.ITDataAcceptRequireService;
import com.caizhi.common.utils.poi.ExcelUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.caizhi.common.core.page.TableDataInfo;

/**
 * 需求接收Controller
 * 
 * @author caizhi
 * @date 2021-03-04
 */
@RestController
@RequestMapping("/basics/require")
public class TDataAcceptRequireController extends BaseController
{
    @Autowired
    private ITDataAcceptRequireService tDataAcceptRequireService;

    /**
     * 查询需求接收列表
     */
    @PreAuthorize("@ss.hasPermi('basics:require:list')")
    @GetMapping("/list")
    @ApiOperation("查询需求接收列表")
	@ApiImplicitParam(name = "tDataAcceptRequire", value = "查询需求接收列表", dataType = "TDataAcceptRequire")
    public TableDataInfo list(TDataAcceptRequire tDataAcceptRequire)
    {
        startPage();
        List<TDataAcceptRequire> list = tDataAcceptRequireService.selectTDataAcceptRequireList(tDataAcceptRequire);
        return getDataTable(list);
    }

    /**
     * 导出需求接收列表
     */
    @PreAuthorize("@ss.hasPermi('basics:require:export')")
    @Log(title = "需求接收", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("导出需求接收列表")
	@ApiImplicitParam(name = "tDataAcceptRequire", value = "导出需求接收列表", dataType = "TDataAcceptRequire")
    public AjaxResult export(TDataAcceptRequire tDataAcceptRequire)
    {
        List<TDataAcceptRequire> list = tDataAcceptRequireService.selectTDataAcceptRequireList(tDataAcceptRequire);
        ExcelUtil<TDataAcceptRequire> util = new ExcelUtil<TDataAcceptRequire>(TDataAcceptRequire.class);
        return util.exportExcel(list, "require");
    }

    /**
     * 获取需求接收详细信息
     */
    @PreAuthorize("@ss.hasPermi('basics:require:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取需求接收详细信息")
	@ApiImplicitParam(name = "id", value = "获取需求接收详细信息", dataType = "long")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tDataAcceptRequireService.selectTDataAcceptRequireById(id));
    }

    /**
     * 新增需求接收
     */
    @PreAuthorize("@ss.hasPermi('basics:require:add')")
    @Log(title = "需求接收", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增需求接收")
	@ApiImplicitParam(name = "tDataAcceptRequire", value = "新增需求接收", dataType = "TDataAcceptRequire")
    public AjaxResult add(@RequestBody TDataAcceptRequire tDataAcceptRequire)
    {
        return toAjax(tDataAcceptRequireService.insertTDataAcceptRequire(tDataAcceptRequire));
    }

    /**
     * 修改需求接收
     */
    @PreAuthorize("@ss.hasPermi('basics:require:edit')")
    @Log(title = "需求接收", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改需求接收")
	@ApiImplicitParam(name = "tDataAcceptRequire", value = "修改需求接收", dataType = "TDataAcceptRequire")
    public AjaxResult edit(@RequestBody TDataAcceptRequire tDataAcceptRequire)
    {
        return toAjax(tDataAcceptRequireService.updateTDataAcceptRequire(tDataAcceptRequire));
    }
    
    /**
     * 接收需求
     */
    @PreAuthorize("@ss.hasPermi('basics:require:edit')")
    @Log(title = "接收需求", businessType = BusinessType.UPDATE)
    @PutMapping("acceptRequire")
    @ApiOperation("接收需求")
	@ApiImplicitParam(name = "tDataAcceptRequire", value = "接收需求", dataType = "TDataAcceptRequire")
    public AjaxResult acceptRequire(@RequestBody TDataAcceptRequire tDataAcceptRequire)
    {
    	tDataAcceptRequire.setStatus(AcceptRequireConstants.TWO);
    	tDataAcceptRequire.setRequireId(tDataAcceptRequire.getId());
    	tDataAcceptRequire.setUpdateTime(new Date());
        return toAjax(tDataAcceptRequireService.acceptOrRefuseRequire(tDataAcceptRequire));
    }
    
    /**
     * 拒绝需求
     */
    @PreAuthorize("@ss.hasPermi('basics:require:edit')")
    @Log(title = "拒绝需求", businessType = BusinessType.UPDATE)
    @PutMapping("refuseRequire")
    @ApiOperation("拒绝需求")
	@ApiImplicitParam(name = "tDataAcceptRequire", value = "拒绝需求", dataType = "TDataAcceptRequire")
    public AjaxResult refuseRequire(@RequestBody TDataAcceptRequire tDataAcceptRequire)
    {
    	tDataAcceptRequire.setStatus(AcceptRequireConstants.THREE);
    	tDataAcceptRequire.setRequireId(tDataAcceptRequire.getId());
    	tDataAcceptRequire.setUpdateTime(new Date());
        return toAjax(tDataAcceptRequireService.acceptOrRefuseRequire(tDataAcceptRequire));
    }

    /**
     * 删除需求接收
     */
    @PreAuthorize("@ss.hasPermi('basics:require:remove')")
    @Log(title = "需求接收", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除需求接收")
	@ApiImplicitParam(name = "ids", value = "删除需求接收", dataType = "array")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tDataAcceptRequireService.deleteTDataAcceptRequireByIds(ids));
    }
    
    /**
     * 查询待接收，已接受，已拒收的需求列表
     */
    @PreAuthorize("@ss.hasPermi('basics:require:list')")
    @GetMapping("/selectAcceptOrRefuseRequire")
    @ApiOperation("查询查询待接收，已接受，已拒收的需求列表")
	@ApiImplicitParam(name = "status", value = "查询待接收，已接受，已拒收的需求列表" ,dataType = "string")
    public TableDataInfo selectAcceptOrRefuseRequire(String status)
    {
        startPage();
        List<TDataAcceptRequireDTO> list = tDataAcceptRequireService.selectAcceptOrRefuseRequire(status);
        return getDataTable(list);
    }
    
    /**
     * 查询已关闭的需求列表
     */
    @PreAuthorize("@ss.hasPermi('basics:require:list')")
    @GetMapping("/selectCloseRequire")
    @ApiOperation("查询已关闭的需求列表")
	@ApiImplicitParam(value = "查询已关闭的需求列表" )
    public TableDataInfo selectCloseRequire()
    {
        startPage();
        List<TDataAcceptRequireDTO> list = tDataAcceptRequireService.selectCloseRequire();
        return getDataTable(list);
    }
    
    /**
     * 查询所有的需求
     */
    @PreAuthorize("@ss.hasPermi('basics:require:list')")
    @GetMapping("/selectAllRequire")
    @ApiOperation("查询所有的需求")
	@ApiImplicitParam(value = "查询所有的需求" )
    public TableDataInfo selectAllRequire()
    {
        startPage();
        List<TDataAcceptRequireDTO> list = tDataAcceptRequireService.selectAllRequire();
        return getDataTable(list);
    }
}
