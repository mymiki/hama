package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.User;
import cc.mrbird.febs.hama.service.IUserService;
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
 * @date 2020-08-09 19:51:09
 */
@Slf4j
@Validated
@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "user")
    public String userIndex(){
        return FebsUtil.view("user/user");
    }

    @GetMapping("user")
    @ResponseBody
    @RequiresPermissions("user:list")
    public FebsResponse getAllUsers(User user) {
        return new FebsResponse().success().data(userService.findUsers(user));
    }

    @GetMapping("user/list")
    @ResponseBody
    @RequiresPermissions("user:list")
    public FebsResponse userList(QueryRequest request, User user) {
        Map<String, Object> dataTable = getDataTable(this.userService.findUsers(request, user));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增User")
    @PostMapping("user")
    @ResponseBody
    @RequiresPermissions("user:add")
    public FebsResponse addUser(@Valid User user) throws FebsException {
        try {
            this.userService.createUser(user);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增User失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除User")
    @GetMapping("user/delete")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public FebsResponse deleteUser(User user) throws FebsException {
        try {
            this.userService.deleteUser(user);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除User失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改User")
    @PostMapping("user/update")
    @ResponseBody
    @RequiresPermissions("user:update")
    public FebsResponse updateUser(User user) throws FebsException {
        try {
            this.userService.updateUser(user);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改User失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("user/excel")
    @ResponseBody
    @RequiresPermissions("user:export")
    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) throws FebsException {
        try {
            List<User> users = this.userService.findUsers(queryRequest, user).getRecords();
            ExcelKit.$Export(User.class, response).downXlsx(users, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
