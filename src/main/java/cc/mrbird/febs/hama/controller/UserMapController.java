package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.UserMap;
import cc.mrbird.febs.hama.service.IUserMapService;
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
 * 用户树关系表 Controller
 *
 * @author MrBird
 * @date 2020-08-09 19:51:47
 */
@Slf4j
@Validated
@Controller
public class UserMapController extends BaseController {

    @Autowired
    private IUserMapService userMapService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "userMap")
    public String userMapIndex(){
        return FebsUtil.view("userMap/userMap");
    }

    @GetMapping("userMap")
    @ResponseBody
    @RequiresPermissions("userMap:list")
    public FebsResponse getAllUserMaps(UserMap userMap) {
        return new FebsResponse().success().data(userMapService.findUserMaps(userMap));
    }

    @GetMapping("userMap/list")
    @ResponseBody
    @RequiresPermissions("userMap:list")
    public FebsResponse userMapList(QueryRequest request, UserMap userMap) {
        Map<String, Object> dataTable = getDataTable(this.userMapService.findUserMaps(request, userMap));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增UserMap")
    @PostMapping("userMap")
    @ResponseBody
    @RequiresPermissions("userMap:add")
    public FebsResponse addUserMap(@Valid UserMap userMap) throws FebsException {
        try {
            this.userMapService.createUserMap(userMap);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增UserMap失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除UserMap")
    @GetMapping("userMap/delete")
    @ResponseBody
    @RequiresPermissions("userMap:delete")
    public FebsResponse deleteUserMap(UserMap userMap) throws FebsException {
        try {
            this.userMapService.deleteUserMap(userMap);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除UserMap失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改UserMap")
    @PostMapping("userMap/update")
    @ResponseBody
    @RequiresPermissions("userMap:update")
    public FebsResponse updateUserMap(UserMap userMap) throws FebsException {
        try {
            this.userMapService.updateUserMap(userMap);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改UserMap失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("userMap/excel")
    @ResponseBody
    @RequiresPermissions("userMap:export")
    public void export(QueryRequest queryRequest, UserMap userMap, HttpServletResponse response) throws FebsException {
        try {
            List<UserMap> userMaps = this.userMapService.findUserMaps(queryRequest, userMap).getRecords();
            ExcelKit.$Export(UserMap.class, response).downXlsx(userMaps, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
