package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.UserPayInfo;
import cc.mrbird.febs.hama.service.IUserPayInfoService;
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
 * @date 2020-08-09 19:51:51
 */
@Slf4j
@Validated
@Controller
public class UserPayInfoController extends BaseController {

    @Autowired
    private IUserPayInfoService userPayInfoService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "userPayInfo")
    public String userPayInfoIndex(){
        return FebsUtil.view("userPayInfo/userPayInfo");
    }

    @GetMapping("userPayInfo")
    @ResponseBody
    @RequiresPermissions("userPayInfo:list")
    public FebsResponse getAllUserPayInfos(UserPayInfo userPayInfo) {
        return new FebsResponse().success().data(userPayInfoService.findUserPayInfos(userPayInfo));
    }

    @GetMapping("userPayInfo/list")
    @ResponseBody
    @RequiresPermissions("userPayInfo:list")
    public FebsResponse userPayInfoList(QueryRequest request, UserPayInfo userPayInfo) {
        Map<String, Object> dataTable = getDataTable(this.userPayInfoService.findUserPayInfos(request, userPayInfo));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增UserPayInfo")
    @PostMapping("userPayInfo")
    @ResponseBody
    @RequiresPermissions("userPayInfo:add")
    public FebsResponse addUserPayInfo(@Valid UserPayInfo userPayInfo) throws FebsException {
        try {
            this.userPayInfoService.createUserPayInfo(userPayInfo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增UserPayInfo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除UserPayInfo")
    @GetMapping("userPayInfo/delete")
    @ResponseBody
    @RequiresPermissions("userPayInfo:delete")
    public FebsResponse deleteUserPayInfo(UserPayInfo userPayInfo) throws FebsException {
        try {
            this.userPayInfoService.deleteUserPayInfo(userPayInfo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除UserPayInfo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改UserPayInfo")
    @PostMapping("userPayInfo/update")
    @ResponseBody
    @RequiresPermissions("userPayInfo:update")
    public FebsResponse updateUserPayInfo(UserPayInfo userPayInfo) throws FebsException {
        try {
            this.userPayInfoService.updateUserPayInfo(userPayInfo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改UserPayInfo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("userPayInfo/excel")
    @ResponseBody
    @RequiresPermissions("userPayInfo:export")
    public void export(QueryRequest queryRequest, UserPayInfo userPayInfo, HttpServletResponse response) throws FebsException {
        try {
            List<UserPayInfo> userPayInfos = this.userPayInfoService.findUserPayInfos(queryRequest, userPayInfo).getRecords();
            ExcelKit.$Export(UserPayInfo.class, response).downXlsx(userPayInfos, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
