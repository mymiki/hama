package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.hama.entity.UserPayInfo;
import cc.mrbird.febs.hama.mapper.UserPayInfoMapper;
import cc.mrbird.febs.hama.service.IUserPayInfoService;
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
 * @date 2020-08-09 19:51:51
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserPayInfoServiceImpl extends ServiceImpl<UserPayInfoMapper, UserPayInfo> implements IUserPayInfoService {

    @Autowired
    private UserPayInfoMapper userPayInfoMapper;

    @Override
    public IPage<UserPayInfo> findUserPayInfos(QueryRequest request, UserPayInfo userPayInfo) {
        LambdaQueryWrapper<UserPayInfo> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<UserPayInfo> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<UserPayInfo> findUserPayInfos(UserPayInfo userPayInfo) {
	    LambdaQueryWrapper<UserPayInfo> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createUserPayInfo(UserPayInfo userPayInfo) {
        this.save(userPayInfo);
    }

    @Override
    @Transactional
    public void updateUserPayInfo(UserPayInfo userPayInfo) {
        this.saveOrUpdate(userPayInfo);
    }

    @Override
    @Transactional
    public void deleteUserPayInfo(UserPayInfo userPayInfo) {
        LambdaQueryWrapper<UserPayInfo> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
