package cc.mrbird.febs.hama.service.impl;

import cc.mrbird.febs.hama.entity.User;
import cc.mrbird.febs.hama.mapper.UserMapper;
import cc.mrbird.febs.hama.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 用户表 Service实现
 *
 * @author MrBird
 * @date 2020-08-09 19:51:09
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> findUsers(QueryRequest request, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<User> findUsers(User user) {
	    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        this.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
