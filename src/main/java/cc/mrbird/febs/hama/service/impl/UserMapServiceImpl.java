package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.hama.entity.UserMap;
import cc.mrbird.febs.hama.mapper.UserMapMapper;
import cc.mrbird.febs.hama.service.IUserMapService;
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
 * 用户树关系表 Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:47
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserMapServiceImpl extends ServiceImpl<UserMapMapper, UserMap> implements IUserMapService {

    @Autowired
    private UserMapMapper userMapMapper;

    @Override
    public IPage<UserMap> findUserMaps(QueryRequest request, UserMap userMap) {
        LambdaQueryWrapper<UserMap> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<UserMap> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<UserMap> findUserMaps(UserMap userMap) {
	    LambdaQueryWrapper<UserMap> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createUserMap(UserMap userMap) {
        this.save(userMap);
    }

    @Override
    @Transactional
    public void updateUserMap(UserMap userMap) {
        this.saveOrUpdate(userMap);
    }

    @Override
    @Transactional
    public void deleteUserMap(UserMap userMap) {
        LambdaQueryWrapper<UserMap> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
