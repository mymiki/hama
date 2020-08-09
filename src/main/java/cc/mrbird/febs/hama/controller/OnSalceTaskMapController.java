package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.OnSalceTaskMap;
import cc.mrbird.febs.hama.service.IOnSalceTaskMapService;
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
 * 执行计划中胡商品列表 Controller
 *
 * @author MrBird
 * @date 2020-08-09 19:51:14
 */
@Slf4j
@Validated
@Controller
public class OnSalceTaskMapController extends BaseController {

    @Autowired
    private IOnSalceTaskMapService onSalceTaskMapService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "onSalceTaskMap")
    public String onSalceTaskMapIndex(){
        return FebsUtil.view("onSalceTaskMap/onSalceTaskMap");
    }

    @GetMapping("onSalceTaskMap")
    @ResponseBody
    @RequiresPermissions("onSalceTaskMap:list")
    public FebsResponse getAllOnSalceTaskMaps(OnSalceTaskMap onSalceTaskMap) {
        return new FebsResponse().success().data(onSalceTaskMapService.findOnSalceTaskMaps(onSalceTaskMap));
    }

    @GetMapping("onSalceTaskMap/list")
    @ResponseBody
    @RequiresPermissions("onSalceTaskMap:list")
    public FebsResponse onSalceTaskMapList(QueryRequest request, OnSalceTaskMap onSalceTaskMap) {
        Map<String, Object> dataTable = getDataTable(this.onSalceTaskMapService.findOnSalceTaskMaps(request, onSalceTaskMap));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增OnSalceTaskMap")
    @PostMapping("onSalceTaskMap")
    @ResponseBody
    @RequiresPermissions("onSalceTaskMap:add")
    public FebsResponse addOnSalceTaskMap(@Valid OnSalceTaskMap onSalceTaskMap) throws FebsException {
        try {
            this.onSalceTaskMapService.createOnSalceTaskMap(onSalceTaskMap);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增OnSalceTaskMap失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除OnSalceTaskMap")
    @GetMapping("onSalceTaskMap/delete")
    @ResponseBody
    @RequiresPermissions("onSalceTaskMap:delete")
    public FebsResponse deleteOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap) throws FebsException {
        try {
            this.onSalceTaskMapService.deleteOnSalceTaskMap(onSalceTaskMap);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除OnSalceTaskMap失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改OnSalceTaskMap")
    @PostMapping("onSalceTaskMap/update")
    @ResponseBody
    @RequiresPermissions("onSalceTaskMap:update")
    public FebsResponse updateOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap) throws FebsException {
        try {
            this.onSalceTaskMapService.updateOnSalceTaskMap(onSalceTaskMap);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改OnSalceTaskMap失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("onSalceTaskMap/excel")
    @ResponseBody
    @RequiresPermissions("onSalceTaskMap:export")
    public void export(QueryRequest queryRequest, OnSalceTaskMap onSalceTaskMap, HttpServletResponse response) throws FebsException {
        try {
            List<OnSalceTaskMap> onSalceTaskMaps = this.onSalceTaskMapService.findOnSalceTaskMaps(queryRequest, onSalceTaskMap).getRecords();
            ExcelKit.$Export(OnSalceTaskMap.class, response).downXlsx(onSalceTaskMaps, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
