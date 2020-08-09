package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.hama.entity.UserCapital;
import cc.mrbird.febs.hama.mapper.UserCapitalMapper;
import cc.mrbird.febs.hama.service.IUserCapitalService;
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
 * 用户资产表 Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:39
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserCapitalServiceImpl extends ServiceImpl<UserCapitalMapper, UserCapital> implements IUserCapitalService {

    @Autowired
    private UserCapitalMapper userCapitalMapper;

    @Override
    public IPage<UserCapital> findUserCapitals(QueryRequest request, UserCapital userCapital) {
        LambdaQueryWrapper<UserCapital> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<UserCapital> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<UserCapital> findUserCapitals(UserCapital userCapital) {
	    LambdaQueryWrapper<UserCapital> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createUserCapital(UserCapital userCapital) {
        this.save(userCapital);
    }

    @Override
    @Transactional
    public void updateUserCapital(UserCapital userCapital) {
        this.saveOrUpdate(userCapital);
    }

    @Override
    @Transactional
    public void deleteUserCapital(UserCapital userCapital) {
        LambdaQueryWrapper<UserCapital> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
