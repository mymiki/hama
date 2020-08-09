package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.hama.entity.Sku;
import cc.mrbird.febs.hama.mapper.SkuMapper;
import cc.mrbird.febs.hama.service.ISkuService;
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
 * @date 2020-08-09 19:51:25
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public IPage<Sku> findSkus(QueryRequest request, Sku sku) {
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Sku> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Sku> findSkus(Sku sku) {
	    LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createSku(Sku sku) {
        this.save(sku);
    }

    @Override
    @Transactional
    public void updateSku(Sku sku) {
        this.saveOrUpdate(sku);
    }

    @Override
    @Transactional
    public void deleteSku(Sku sku) {
        LambdaQueryWrapper<Sku> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
