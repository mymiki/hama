package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.UserCapital;
import cc.mrbird.febs.hama.service.IUserCapitalService;
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
 * 用户资产表 Controller
 *
 * @author MrBird
 * @date 2020-08-09 19:51:39
 */
@Slf4j
@Validated
@Controller
public class UserCapitalController extends BaseController {

    @Autowired
    private IUserCapitalService userCapitalService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "userCapital")
    public String userCapitalIndex(){
        return FebsUtil.view("userCapital/userCapital");
    }

    @GetMapping("userCapital")
    @ResponseBody
    @RequiresPermissions("userCapital:list")
    public FebsResponse getAllUserCapitals(UserCapital userCapital) {
        return new FebsResponse().success().data(userCapitalService.findUserCapitals(userCapital));
    }

    @GetMapping("userCapital/list")
    @ResponseBody
    @RequiresPermissions("userCapital:list")
    public FebsResponse userCapitalList(QueryRequest request, UserCapital userCapital) {
        Map<String, Object> dataTable = getDataTable(this.userCapitalService.findUserCapitals(request, userCapital));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增UserCapital")
    @PostMapping("userCapital")
    @ResponseBody
    @RequiresPermissions("userCapital:add")
    public FebsResponse addUserCapital(@Valid UserCapital userCapital) throws FebsException {
        try {
            this.userCapitalService.createUserCapital(userCapital);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增UserCapital失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除UserCapital")
    @GetMapping("userCapital/delete")
    @ResponseBody
    @RequiresPermissions("userCapital:delete")
    public FebsResponse deleteUserCapital(UserCapital userCapital) throws FebsException {
        try {
            this.userCapitalService.deleteUserCapital(userCapital);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除UserCapital失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改UserCapital")
    @PostMapping("userCapital/update")
    @ResponseBody
    @RequiresPermissions("userCapital:update")
    public FebsResponse updateUserCapital(UserCapital userCapital) throws FebsException {
        try {
            this.userCapitalService.updateUserCapital(userCapital);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改UserCapital失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("userCapital/excel")
    @ResponseBody
    @RequiresPermissions("userCapital:export")
    public void export(QueryRequest queryRequest, UserCapital userCapital, HttpServletResponse response) throws FebsException {
        try {
            List<UserCapital> userCapitals = this.userCapitalService.findUserCapitals(queryRequest, userCapital).getRecords();
            ExcelKit.$Export(UserCapital.class, response).downXlsx(userCapitals, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
