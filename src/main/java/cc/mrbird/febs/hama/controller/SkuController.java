package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.Sku;
import cc.mrbird.febs.hama.service.ISkuService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author MrBird
 * @date 2020-08-09 19:51:25
 */
@Slf4j
@Validated
@Controller
public class SkuController extends BaseController {

    @Autowired
    private ISkuService skuService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "sku")
    public String skuIndex(){
        return FebsUtil.view("sku/sku");
    }

    @GetMapping("sku")
    @ResponseBody
    @RequiresPermissions("sku:list")
    public FebsResponse getAllSkus(Sku sku) {
        return new FebsResponse().success().data(skuService.findSkus(sku));
    }

    @GetMapping("sku/list")
    @ResponseBody
    @RequiresPermissions("sku:list")
    public FebsResponse skuList(QueryRequest request, Sku sku) {
        Map<String, Object> dataTable = getDataTable(this.skuService.findSkus(request, sku));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Sku")
    @PostMapping("sku")
    @ResponseBody
    @RequiresPermissions("sku:add")
    public FebsResponse addSku(@Valid Sku sku) throws FebsException {
        try {
            this.skuService.createSku(sku);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Sku失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Sku")
    @GetMapping("sku/delete")
    @ResponseBody
    @RequiresPermissions("sku:delete")
    public FebsResponse deleteSku(Sku sku) throws FebsException {
        try {
            this.skuService.deleteSku(sku);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Sku失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Sku")
    @PostMapping("sku/update")
    @ResponseBody
    @RequiresPermissions("sku:update")
    public FebsResponse updateSku(Sku sku) throws FebsException {
        try {
            this.skuService.updateSku(sku);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Sku失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("sku/excel")
    @ResponseBody
    @RequiresPermissions("sku:export")
    public void export(QueryRequest queryRequest, Sku sku, HttpServletResponse response) throws FebsException {
        try {
            List<Sku> skus = this.skuService.findSkus(queryRequest, sku).getRecords();
            ExcelKit.$Export(Sku.class, response).downXlsx(skus, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
