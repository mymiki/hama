package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.Spu;
import cc.mrbird.febs.hama.service.ISpuService;
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
 * @date 2020-08-09 19:51:12
 */
@Slf4j
@Validated
@Controller
public class SpuController extends BaseController {

    @Autowired
    private ISpuService spuService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "spu")
    public String spuIndex(){
        return FebsUtil.view("spu/spu");
    }

    @GetMapping("spu")
    @ResponseBody
    @RequiresPermissions("spu:list")
    public FebsResponse getAllSpus(Spu spu) {
        return new FebsResponse().success().data(spuService.findSpus(spu));
    }

    @GetMapping("spu/list")
    @ResponseBody
    @RequiresPermissions("spu:list")
    public FebsResponse spuList(QueryRequest request, Spu spu) {
        Map<String, Object> dataTable = getDataTable(this.spuService.findSpus(request, spu));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Spu")
    @PostMapping("spu")
    @ResponseBody
    @RequiresPermissions("spu:add")
    public FebsResponse addSpu(@Valid Spu spu) throws FebsException {
        try {
            this.spuService.createSpu(spu);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Spu失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Spu")
    @GetMapping("spu/delete")
    @ResponseBody
    @RequiresPermissions("spu:delete")
    public FebsResponse deleteSpu(Spu spu) throws FebsException {
        try {
            this.spuService.deleteSpu(spu);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Spu失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Spu")
    @PostMapping("spu/update")
    @ResponseBody
    @RequiresPermissions("spu:update")
    public FebsResponse updateSpu(Spu spu) throws FebsException {
        try {
            this.spuService.updateSpu(spu);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Spu失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("spu/excel")
    @ResponseBody
    @RequiresPermissions("spu:export")
    public void export(QueryRequest queryRequest, Spu spu, HttpServletResponse response) throws FebsException {
        try {
            List<Spu> spus = this.spuService.findSpus(queryRequest, spu).getRecords();
            ExcelKit.$Export(Spu.class, response).downXlsx(spus, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
