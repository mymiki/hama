package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.hama.entity.Spu;
import cc.mrbird.febs.hama.mapper.SpuMapper;
import cc.mrbird.febs.hama.service.ISpuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:12
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public IPage<Spu> findSpus(QueryRequest request, Spu spu) {
        LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Spu> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Spu> findSpus(Spu spu) {
	    LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createSpu(Spu spu) {
        this.save(spu);
    }

    @Override
    @Transactional
    public void updateSpu(Spu spu) {
        this.saveOrUpdate(spu);
    }

    @Override
    @Transactional
    public void deleteSpu(Spu spu) {
        LambdaQueryWrapper<Spu> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
