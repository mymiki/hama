package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.OnSaleTask;
import cc.mrbird.febs.hama.service.IOnSaleTaskService;
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
 * 上架任务执行计划表 Controller
 *
 * @author MrBird
 * @date 2020-08-09 19:51:16
 */
@Slf4j
@Validated
@Controller
public class OnSaleTaskController extends BaseController {

    @Autowired
    private IOnSaleTaskService onSaleTaskService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "onSaleTask")
    public String onSaleTaskIndex(){
        return FebsUtil.view("onSaleTask/onSaleTask");
    }

    @GetMapping("onSaleTask")
    @ResponseBody
    @RequiresPermissions("onSaleTask:list")
    public FebsResponse getAllOnSaleTasks(OnSaleTask onSaleTask) {
        return new FebsResponse().success().data(onSaleTaskService.findOnSaleTasks(onSaleTask));
    }

    @GetMapping("onSaleTask/list")
    @ResponseBody
    @RequiresPermissions("onSaleTask:list")
    public FebsResponse onSaleTaskList(QueryRequest request, OnSaleTask onSaleTask) {
        Map<String, Object> dataTable = getDataTable(this.onSaleTaskService.findOnSaleTasks(request, onSaleTask));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增OnSaleTask")
    @PostMapping("onSaleTask")
    @ResponseBody
    @RequiresPermissions("onSaleTask:add")
    public FebsResponse addOnSaleTask(@Valid OnSaleTask onSaleTask) throws FebsException {
        try {
            this.onSaleTaskService.createOnSaleTask(onSaleTask);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增OnSaleTask失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除OnSaleTask")
    @GetMapping("onSaleTask/delete")
    @ResponseBody
    @RequiresPermissions("onSaleTask:delete")
    public FebsResponse deleteOnSaleTask(OnSaleTask onSaleTask) throws FebsException {
        try {
            this.onSaleTaskService.deleteOnSaleTask(onSaleTask);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除OnSaleTask失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改OnSaleTask")
    @PostMapping("onSaleTask/update")
    @ResponseBody
    @RequiresPermissions("onSaleTask:update")
    public FebsResponse updateOnSaleTask(OnSaleTask onSaleTask) throws FebsException {
        try {
            this.onSaleTaskService.updateOnSaleTask(onSaleTask);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改OnSaleTask失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("onSaleTask/excel")
    @ResponseBody
    @RequiresPermissions("onSaleTask:export")
    public void export(QueryRequest queryRequest, OnSaleTask onSaleTask, HttpServletResponse response) throws FebsException {
        try {
            List<OnSaleTask> onSaleTasks = this.onSaleTaskService.findOnSaleTasks(queryRequest, onSaleTask).getRecords();
            ExcelKit.$Export(OnSaleTask.class, response).downXlsx(onSaleTasks, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
