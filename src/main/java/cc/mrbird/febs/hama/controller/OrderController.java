package cc.mrbird.febs.hama.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.hama.entity.Order;
import cc.mrbird.febs.hama.service.IOrderService;
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
 * @date 2020-08-09 19:51:53
 */
@Slf4j
@Validated
@Controller
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "order")
    public String orderIndex(){
        return FebsUtil.view("order/order");
    }

    @GetMapping("order")
    @ResponseBody
    @RequiresPermissions("order:list")
    public FebsResponse getAllOrders(Order order) {
        return new FebsResponse().success().data(orderService.findOrders(order));
    }

    @GetMapping("order/list")
    @ResponseBody
    @RequiresPermissions("order:list")
    public FebsResponse orderList(QueryRequest request, Order order) {
        Map<String, Object> dataTable = getDataTable(this.orderService.findOrders(request, order));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Order")
    @PostMapping("order")
    @ResponseBody
    @RequiresPermissions("order:add")
    public FebsResponse addOrder(@Valid Order order) throws FebsException {
        try {
            this.orderService.createOrder(order);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Order失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Order")
    @GetMapping("order/delete")
    @ResponseBody
    @RequiresPermissions("order:delete")
    public FebsResponse deleteOrder(Order order) throws FebsException {
        try {
            this.orderService.deleteOrder(order);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Order失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Order")
    @PostMapping("order/update")
    @ResponseBody
    @RequiresPermissions("order:update")
    public FebsResponse updateOrder(Order order) throws FebsException {
        try {
            this.orderService.updateOrder(order);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Order失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("order/excel")
    @ResponseBody
    @RequiresPermissions("order:export")
    public void export(QueryRequest queryRequest, Order order, HttpServletResponse response) throws FebsException {
        try {
            List<Order> orders = this.orderService.findOrders(queryRequest, order).getRecords();
            ExcelKit.$Export(Order.class, response).downXlsx(orders, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
