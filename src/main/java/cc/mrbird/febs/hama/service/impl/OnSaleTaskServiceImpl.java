package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.hama.entity.OnSaleTask;
import cc.mrbird.febs.hama.mapper.OnSaleTaskMapper;
import cc.mrbird.febs.hama.service.IOnSaleTaskService;
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
 * 上架任务执行计划表 Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OnSaleTaskServiceImpl extends ServiceImpl<OnSaleTaskMapper, OnSaleTask> implements IOnSaleTaskService {

    @Autowired
    private OnSaleTaskMapper onSaleTaskMapper;

    @Override
    public IPage<OnSaleTask> findOnSaleTasks(QueryRequest request, OnSaleTask onSaleTask) {
        LambdaQueryWrapper<OnSaleTask> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<OnSaleTask> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OnSaleTask> findOnSaleTasks(OnSaleTask onSaleTask) {
	    LambdaQueryWrapper<OnSaleTask> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOnSaleTask(OnSaleTask onSaleTask) {
        this.save(onSaleTask);
    }

    @Override
    @Transactional
    public void updateOnSaleTask(OnSaleTask onSaleTask) {
        this.saveOrUpdate(onSaleTask);
    }

    @Override
    @Transactional
    public void deleteOnSaleTask(OnSaleTask onSaleTask) {
        LambdaQueryWrapper<OnSaleTask> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
