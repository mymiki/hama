package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.hama.entity.OnSalceTaskMap;
import cc.mrbird.febs.hama.mapper.OnSalceTaskMapMapper;
import cc.mrbird.febs.hama.service.IOnSalceTaskMapService;
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
 * 执行计划中胡商品列表 Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:14
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OnSalceTaskMapServiceImpl extends ServiceImpl<OnSalceTaskMapMapper, OnSalceTaskMap> implements IOnSalceTaskMapService {

    @Autowired
    private OnSalceTaskMapMapper onSalceTaskMapMapper;

    @Override
    public IPage<OnSalceTaskMap> findOnSalceTaskMaps(QueryRequest request, OnSalceTaskMap onSalceTaskMap) {
        LambdaQueryWrapper<OnSalceTaskMap> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<OnSalceTaskMap> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OnSalceTaskMap> findOnSalceTaskMaps(OnSalceTaskMap onSalceTaskMap) {
	    LambdaQueryWrapper<OnSalceTaskMap> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap) {
        this.save(onSalceTaskMap);
    }

    @Override
    @Transactional
    public void updateOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap) {
        this.saveOrUpdate(onSalceTaskMap);
    }

    @Override
    @Transactional
    public void deleteOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap) {
        LambdaQueryWrapper<OnSalceTaskMap> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
