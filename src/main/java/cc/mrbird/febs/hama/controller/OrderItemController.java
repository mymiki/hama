package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.OrderItem;
import cc.mrbird.febs.hama.service.IOrderItemService;
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
 * @date 2020-08-09 19:51:20
 */
@Slf4j
@Validated
@Controller
public class OrderItemController extends BaseController {

    @Autowired
    private IOrderItemService orderItemService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "orderItem")
    public String orderItemIndex(){
        return FebsUtil.view("orderItem/orderItem");
    }

    @GetMapping("orderItem")
    @ResponseBody
    @RequiresPermissions("orderItem:list")
    public FebsResponse getAllOrderItems(OrderItem orderItem) {
        return new FebsResponse().success().data(orderItemService.findOrderItems(orderItem));
    }

    @GetMapping("orderItem/list")
    @ResponseBody
    @RequiresPermissions("orderItem:list")
    public FebsResponse orderItemList(QueryRequest request, OrderItem orderItem) {
        Map<String, Object> dataTable = getDataTable(this.orderItemService.findOrderItems(request, orderItem));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增OrderItem")
    @PostMapping("orderItem")
    @ResponseBody
    @RequiresPermissions("orderItem:add")
    public FebsResponse addOrderItem(@Valid OrderItem orderItem) throws FebsException {
        try {
            this.orderItemService.createOrderItem(orderItem);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增OrderItem失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除OrderItem")
    @GetMapping("orderItem/delete")
    @ResponseBody
    @RequiresPermissions("orderItem:delete")
    public FebsResponse deleteOrderItem(OrderItem orderItem) throws FebsException {
        try {
            this.orderItemService.deleteOrderItem(orderItem);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除OrderItem失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改OrderItem")
    @PostMapping("orderItem/update")
    @ResponseBody
    @RequiresPermissions("orderItem:update")
    public FebsResponse updateOrderItem(OrderItem orderItem) throws FebsException {
        try {
            this.orderItemService.updateOrderItem(orderItem);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改OrderItem失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("orderItem/excel")
    @ResponseBody
    @RequiresPermissions("orderItem:export")
    public void export(QueryRequest queryRequest, OrderItem orderItem, HttpServletResponse response) throws FebsException {
        try {
            List<OrderItem> orderItems = this.orderItemService.findOrderItems(queryRequest, orderItem).getRecords();
            ExcelKit.$Export(OrderItem.class, response).downXlsx(orderItems, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
