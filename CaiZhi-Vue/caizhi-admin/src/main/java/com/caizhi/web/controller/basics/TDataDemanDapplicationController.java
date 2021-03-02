package com.caizhi.web.controller.basics;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caizhi.basics.domain.TDataDemanDapplication;
import com.caizhi.basics.service.ITDataDemanDapplicationService;
import com.caizhi.common.annotation.Log;
import com.caizhi.common.constant.ApprvalStatusConstants;
import com.caizhi.common.core.controller.BaseController;
import com.caizhi.common.core.domain.AjaxResult;
import com.caizhi.common.core.page.TableDataInfo;
import com.caizhi.common.enums.BusinessType;
import com.caizhi.common.utils.poi.ExcelUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 需求审批Controller
 * 
 * @author caizhi
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/basics/tDataDemanDapplication")
public class TDataDemanDapplicationController extends BaseController
{
    @Autowired
    private ITDataDemanDapplicationService tDataDemanDapplicationService;

    /**
     * 查询需求审批列表
     */
    @PreAuthorize("@ss.hasPermi('basics:dapplication:list')")
    @GetMapping("/list")
    @ApiOperation("查询需求审批列表")
	@ApiImplicitParam(name = "tDataDemanDapplication", value = "查询需求审批列表", dataType = "TDataDemanDapplication")
    public TableDataInfo list(TDataDemanDapplication tDataDemanDapplication)
    {
        startPage();
        List<TDataDemanDapplication> list = tDataDemanDapplicationService.selectTDataDemanDapplicationList(tDataDemanDapplication);
        return getDataTable(list);
    }

    /**
     * 导出需求审批列表
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:export')")
    @Log(title = "需求审批", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("导出需求审批列表")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "导出需求审批列表", dataType = "TDataDemanDapplication")
    public AjaxResult export(TDataDemanDapplication tDataDemanDapplication)
    {
        List<TDataDemanDapplication> list = tDataDemanDapplicationService.selectTDataDemanDapplicationList(tDataDemanDapplication);
        ExcelUtil<TDataDemanDapplication> util = new ExcelUtil<TDataDemanDapplication>(TDataDemanDapplication.class);
        return util.exportExcel(list, "dapplication");
    }

    /**
     * 获取需求审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取需求审批详细信息")
	@ApiImplicitParam(name = "id", value = "获取需求审批详细信息", dataType = "long")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tDataDemanDapplicationService.selectTDataDemanDapplicationById(id));
    }

    /**
     * 新增需求审批
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:add')")
    @Log(title = "需求审批", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增需求审批")
	@ApiImplicitParam(name = "tDataDemanDapplication", value = "新增需求审批", dataType = "TDataDemanDapplication")
    public AjaxResult add(@RequestBody TDataDemanDapplication tDataDemanDapplication , String flag)
    {
    	//新建数据如果是提交默认是待审批（2），如果是保存未提交默认是起草（1）
    	//flag 的值 保存传1 ，提交传2
    	String status = "";
    	Date createTime = new Date();
    	status = "1".equals(flag) ? ApprvalStatusConstants.ONE : ApprvalStatusConstants.TWO;
    	tDataDemanDapplication.setStatus(status);
    	//创建时间
    	tDataDemanDapplication.setCreateTime(createTime);
        return toAjax(tDataDemanDapplicationService.insertTDataDemanDapplication(tDataDemanDapplication));
    }

    /**
     * 修改需求审批
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:edit')")
    @Log(title = "需求审批", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    @ApiOperation("修改需求审批")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "修改需求审批", dataType = "TDataDemanDapplication")
    public AjaxResult edit(@RequestBody TDataDemanDapplication tDataDemanDapplication)
    {
        return toAjax(tDataDemanDapplicationService.updateTDataDemanDapplication(tDataDemanDapplication));
    }
    
    /**
     * 需求审批-同意
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:appravl')")
    @Log(title = "需求审批-同意", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/appravl")
    @ApiOperation("需求审批-同意")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "需求审批-同意", dataType = "TDataDemanDapplication")
    public AjaxResult appravl(@RequestBody TDataDemanDapplication tDataDemanDapplication)
    {
    	tDataDemanDapplication.setStatus(ApprvalStatusConstants.THREE);
        return toAjax(tDataDemanDapplicationService.updateTDataDemanDapplication(tDataDemanDapplication));
    }
    
    /**
     * 需求审批-驳回
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:reject')")
    @Log(title = "需求审批-驳回", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/reject")
    @ApiOperation("需求审批-驳回")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "需求审批-驳回", dataType = "TDataDemanDapplication")
    public AjaxResult reject(@RequestBody TDataDemanDapplication tDataDemanDapplication)
    {
    	tDataDemanDapplication.setStatus(ApprvalStatusConstants.FOUR);
        return toAjax(tDataDemanDapplicationService.updateTDataDemanDapplication(tDataDemanDapplication));
    }
    
    /**
     * 需求审批-撤回
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:withdraw')")
    @Log(title = "需求审批-撤回", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/withdraw")
    @ApiOperation("需求审批-撤回")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "需求审批-撤回", dataType = "TDataDemanDapplication")
    public AjaxResult withdraw(@RequestBody TDataDemanDapplication tDataDemanDapplication)
    {
    	tDataDemanDapplication.setStatus(ApprvalStatusConstants.FIVE);
        return toAjax(tDataDemanDapplicationService.updateTDataDemanDapplication(tDataDemanDapplication));
    }
    
    /**
     * 需求审批-关闭
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:close')")
    @Log(title = "需求审批-关闭", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/close")
    @ApiOperation("需求审批-关闭")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "需求审批-关闭", dataType = "TDataDemanDapplication")
    public AjaxResult close(@RequestBody TDataDemanDapplication tDataDemanDapplication)
    {
    	tDataDemanDapplication.setStatus(ApprvalStatusConstants.SIX);
        return toAjax(tDataDemanDapplicationService.updateTDataDemanDapplication(tDataDemanDapplication));
    }
    
    /**
     * 需求审批-发布
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:release')")
    @Log(title = "需求审批-发布", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/release")
    @ApiOperation("需求审批-发布")
   	@ApiImplicitParam(name = "tDataDemanDapplication", value = "需求审批-发布", dataType = "TDataDemanDapplication")
    public AjaxResult release(@RequestBody TDataDemanDapplication tDataDemanDapplication)
    {
    	tDataDemanDapplication.setStatus(ApprvalStatusConstants.FOUR);
        return toAjax(tDataDemanDapplicationService.updateTDataDemanDapplication(tDataDemanDapplication));
    }

    /**
     * 删除需求审批
     */
    @PreAuthorize("@ss.hasPermi('basics:tdatademandapplication:remove')")
    @Log(title = "需求审批", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除需求审批")
	@ApiImplicitParam(name = "ids", value = "删除需求审批", dataType = "Array")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tDataDemanDapplicationService.deleteTDataDemanDapplicationByIds(ids));
    }
}

