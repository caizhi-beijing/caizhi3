package com.caizhi.web.controller.basics;

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
import com.caizhi.common.core.controller.BaseController;
import com.caizhi.common.core.domain.AjaxResult;
import com.caizhi.common.enums.BusinessType;
import com.caizhi.basics.domain.TDataCandidateInformation;
import com.caizhi.basics.service.ITDataCandidateInformationService;
import com.caizhi.common.utils.poi.ExcelUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.caizhi.common.core.page.TableDataInfo;

/**
 * 候选人信息Controller
 * 
 * @author caizhi
 * @date 2021-03-04
 */
@RestController
@RequestMapping("/basics/information")
public class TDataCandidateInformationController extends BaseController
{
    @Autowired
    private ITDataCandidateInformationService tDataCandidateInformationService;

    /**
     * 查询候选人信息列表
     */
    @PreAuthorize("@ss.hasPermi('basics:information:list')")
    @GetMapping("/list")
    @ApiOperation("查询候选人信息列表")
	@ApiImplicitParam(name = "tDataCandidateInformation", value = "查询候选人信息列表", dataType = "TDataCandidateInformation")
    public TableDataInfo list(TDataCandidateInformation tDataCandidateInformation)
    {
        startPage();
        List<TDataCandidateInformation> list = tDataCandidateInformationService.selectTDataCandidateInformationList(tDataCandidateInformation);
        return getDataTable(list);
    }

    /**
     * 导出候选人信息列表
     */
    @PreAuthorize("@ss.hasPermi('basics:information:export')")
    @Log(title = "候选人信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("导出候选人信息列表")
   	@ApiImplicitParam(name = "tDataCandidateInformation", value = "导出候选人信息列表", dataType = "TDataCandidateInformation")
    public AjaxResult export(TDataCandidateInformation tDataCandidateInformation)
    {
        List<TDataCandidateInformation> list = tDataCandidateInformationService.selectTDataCandidateInformationList(tDataCandidateInformation);
        ExcelUtil<TDataCandidateInformation> util = new ExcelUtil<TDataCandidateInformation>(TDataCandidateInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 获取候选人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basics:information:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("查询候选人信息详细信息")
   	@ApiImplicitParam(name = "id", value = "查询候选人详细信息", dataType = "long")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tDataCandidateInformationService.selectTDataCandidateInformationById(id));
    }

    /**
     * 新增候选人信息
     */
    @PreAuthorize("@ss.hasPermi('basics:information:add')")
    @Log(title = "候选人信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增候选人信息")
   	@ApiImplicitParam(name = "tDataCandidateInformation", value = "新增候选人信息", dataType = "TDataCandidateInformation")
    public AjaxResult add(@RequestBody TDataCandidateInformation tDataCandidateInformation)
    {
        return toAjax(tDataCandidateInformationService.insertTDataCandidateInformation(tDataCandidateInformation));
    }

    /**
     * 修改候选人信息
     */
    @PreAuthorize("@ss.hasPermi('basics:information:edit')")
    @Log(title = "候选人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改候选人信息")
   	@ApiImplicitParam(name = "tDataCandidateInformation", value = "修改候选人信息", dataType = "TDataCandidateInformation")
    public AjaxResult edit(@RequestBody TDataCandidateInformation tDataCandidateInformation)
    {
        return toAjax(tDataCandidateInformationService.updateTDataCandidateInformation(tDataCandidateInformation));
    }

    /**
     * 删除候选人信息
     */
    @PreAuthorize("@ss.hasPermi('basics:information:remove')")
    @Log(title = "候选人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("导出候选人信息列表")
   	@ApiImplicitParam(name = "ids", value = "导出候选人信息列表", dataType = "Array")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tDataCandidateInformationService.deleteTDataCandidateInformationByIds(ids));
    }
    
    /**
     * 校验候选人是否已经存 (1是已经存，不能新增；0是不存在，可以新增)
     */
    @PreAuthorize("@ss.hasPermi('basics:information:query')")
    @GetMapping(value = "/getCandidateIfExist")
    @ApiOperation("校验候选人是否已经存")
   	@ApiImplicitParam(name = "name,phone", value = "校验候选人是否已经存", dataType = "string")
    public AjaxResult getCandidateIfExist(String name,String phone)
    {
    	int result = 0;
    	TDataCandidateInformation tDataCandidateInformation = tDataCandidateInformationService.getCandidateIfExist(name, phone);
    	if(null !=tDataCandidateInformation) {
    		result = 1;
    	}
        return AjaxResult.success(result);
    }
}
