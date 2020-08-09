package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.hama.entity.Order;
import cc.mrbird.febs.hama.mapper.OrderMapper;
import cc.mrbird.febs.hama.service.IOrderService;
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
 * @date 2020-08-09 19:51:53
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Order> findOrders(QueryRequest request, Order order) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Order> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Order> findOrders(Order order) {
	    LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOrder(Order order) {
        this.save(order);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        this.saveOrUpdate(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Order order) {
        LambdaQueryWrapper<Order> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
