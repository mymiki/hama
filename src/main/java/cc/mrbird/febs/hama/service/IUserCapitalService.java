package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.UserCapital;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户资产表 Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:39
 */
public interface IUserCapitalService extends IService<UserCapital> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param userCapital userCapital
     * @return IPage<UserCapital>
     */
    IPage<UserCapital> findUserCapitals(QueryRequest request, UserCapital userCapital);

    /**
     * 查询（所有）
     *
     * @param userCapital userCapital
     * @return List<UserCapital>
     */
    List<UserCapital> findUserCapitals(UserCapital userCapital);

    /**
     * 新增
     *
     * @param userCapital userCapital
     */
    void createUserCapital(UserCapital userCapital);

    /**
     * 修改
     *
     * @param userCapital userCapital
     */
    void updateUserCapital(UserCapital userCapital);

    /**
     * 删除
     *
     * @param userCapital userCapital
     */
    void deleteUserCapital(UserCapital userCapital);
}
