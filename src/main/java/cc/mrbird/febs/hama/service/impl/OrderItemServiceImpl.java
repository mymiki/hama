package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.hama.entity.OrderItem;
import cc.mrbird.febs.hama.mapper.OrderItemMapper;
import cc.mrbird.febs.hama.service.IOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import cc.mrbird.febs.common.entity.QueryRequest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public IPage<OrderItem> findOrderItems(QueryRequest request, OrderItem orderItem) {
        LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<OrderItem> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OrderItem> findOrderItems(OrderItem orderItem) {
	    LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOrderItem(OrderItem orderItem) {
        this.save(orderItem);
    }

    @Override
    @Transactional
    public void updateOrderItem(OrderItem orderItem) {
        this.saveOrUpdate(orderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(OrderItem orderItem) {
        LambdaQueryWrapper<OrderItem> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
