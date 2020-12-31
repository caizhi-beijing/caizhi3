package com.caizhi.web.controller.basics;

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

import com.caizhi.basics.domain.TSupplier;
import com.caizhi.basics.service.ITSupplierService;
import com.caizhi.common.annotation.Log;
import com.caizhi.common.core.controller.BaseController;
import com.caizhi.common.core.domain.AjaxResult;
import com.caizhi.common.core.page.TableDataInfo;
import com.caizhi.common.enums.BusinessType;
import com.caizhi.common.utils.poi.ExcelUtil;

/**
 * 供应商信息Controller
 * 
 * @author caizhi
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/basics/supplier")
public class TSupplierController extends BaseController
{
    @Autowired
    private ITSupplierService tSupplierService;

    /**
     * 查询供应商信息列表
     */
    @PreAuthorize("@ss.hasPermi('basics:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSupplier tSupplier)
    {
        startPage();
        List<TSupplier> list = tSupplierService.selectTSupplierList(tSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商信息列表
     */
    @PreAuthorize("@ss.hasPermi('basics:supplier:export')")
    @Log(title = "供应商信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSupplier tSupplier)
    {
        List<TSupplier> list = tSupplierService.selectTSupplierList(tSupplier);
        ExcelUtil<TSupplier> util = new ExcelUtil<TSupplier>(TSupplier.class);
        return util.exportExcel(list, "supplier");
    }

    /**
     * 获取供应商信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basics:supplier:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tSupplierService.selectTSupplierById(id));
    }

    /**
     * 新增供应商信息
     */
    @PreAuthorize("@ss.hasPermi('basics:supplier:add')")
    @Log(title = "供应商信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSupplier tSupplier)
    {
        return toAjax(tSupplierService.insertTSupplier(tSupplier));
    }

    /**
     * 修改供应商信息
     */
    @PreAuthorize("@ss.hasPermi('basics:supplier:edit')")
    @Log(title = "供应商信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSupplier tSupplier)
    {
        return toAjax(tSupplierService.updateTSupplier(tSupplier));
    }

    /**
     * 删除供应商信息
     */
    @PreAuthorize("@ss.hasPermi('basics:supplier:remove')")
    @Log(title = "供应商信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tSupplierService.deleteTSupplierByIds(ids));
    }
}
