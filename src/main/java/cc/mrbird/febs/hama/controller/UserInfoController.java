package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.UserInfo;
import cc.mrbird.febs.hama.service.IUserInfoService;
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
 * 用户表 Controller
 *
 * @author MrBird
 * @date 2020-08-10 22:31:04
 */
@Slf4j
@Validated
@Controller
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "userInfo")
    public String userInfoIndex(){
        return FebsUtil.view("userInfo/userInfo");
    }

    @GetMapping("userInfo")
    @ResponseBody
    @RequiresPermissions("userInfo:list")
    public FebsResponse getAllUserInfos(UserInfo userInfo) {
        return new FebsResponse().success().data(userInfoService.findUserInfos(userInfo));
    }

    @GetMapping("userInfo/list")
    @ResponseBody
    @RequiresPermissions("userInfo:list")
    public FebsResponse userInfoList(QueryRequest request, UserInfo userInfo) {
        Map<String, Object> dataTable = getDataTable(this.userInfoService.findUserInfos(request, userInfo));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增UserInfo")
    @PostMapping("userInfo")
    @ResponseBody
    @RequiresPermissions("userInfo:add")
    public FebsResponse addUserInfo(@Valid UserInfo userInfo) throws FebsException {
        try {
            this.userInfoService.createUserInfo(userInfo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增UserInfo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除UserInfo")
    @GetMapping("userInfo/delete")
    @ResponseBody
    @RequiresPermissions("userInfo:delete")
    public FebsResponse deleteUserInfo(UserInfo userInfo) throws FebsException {
        try {
            this.userInfoService.deleteUserInfo(userInfo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除UserInfo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改UserInfo")
    @PostMapping("userInfo/update")
    @ResponseBody
    @RequiresPermissions("userInfo:update")
    public FebsResponse updateUserInfo(UserInfo userInfo) throws FebsException {
        try {
            this.userInfoService.updateUserInfo(userInfo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改UserInfo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("userInfo/excel")
    @ResponseBody
    @RequiresPermissions("userInfo:export")
    public void export(QueryRequest queryRequest, UserInfo userInfo, HttpServletResponse response) throws FebsException {
        try {
            List<UserInfo> userInfos = this.userInfoService.findUserInfos(queryRequest, userInfo).getRecords();
            ExcelKit.$Export(UserInfo.class, response).downXlsx(userInfos, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
