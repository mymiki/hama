package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.OrderItem;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:20
 */
public interface IOrderItemService extends IService<OrderItem> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param orderItem orderItem
     * @return IPage<OrderItem>
     */
    IPage<OrderItem> findOrderItems(QueryRequest request, OrderItem orderItem);

    /**
     * 查询（所有）
     *
     * @param orderItem orderItem
     * @return List<OrderItem>
     */
    List<OrderItem> findOrderItems(OrderItem orderItem);

    /**
     * 新增
     *
     * @param orderItem orderItem
     */
    void createOrderItem(OrderItem orderItem);

    /**
     * 修改
     *
     * @param orderItem orderItem
     */
    void updateOrderItem(OrderItem orderItem);

    /**
     * 删除
     *
     * @param orderItem orderItem
     */
    void deleteOrderItem(OrderItem orderItem);
}
