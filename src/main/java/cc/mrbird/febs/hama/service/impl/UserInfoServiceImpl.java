package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.hama.entity.UserInfo;
import cc.mrbird.febs.hama.mapper.UserInfoMapper;
import cc.mrbird.febs.hama.service.IUserInfoService;
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
 * 用户表 Service实现
 *
 * @author MrBird
 * @date 2020-08-10 22:31:04
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public IPage<UserInfo> findUserInfos(QueryRequest request, UserInfo userInfo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<UserInfo> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<UserInfo> findUserInfos(UserInfo userInfo) {
	    LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createUserInfo(UserInfo userInfo) {
        this.save(userInfo);
    }

    @Override
    @Transactional
    public void updateUserInfo(UserInfo userInfo) {
        this.saveOrUpdate(userInfo);
    }

    @Override
    @Transactional
    public void deleteUserInfo(UserInfo userInfo) {
        LambdaQueryWrapper<UserInfo> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
