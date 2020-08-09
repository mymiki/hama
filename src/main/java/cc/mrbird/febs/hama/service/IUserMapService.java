package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.UserMap;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户树关系表 Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:47
 */
public interface IUserMapService extends IService<UserMap> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param userMap userMap
     * @return IPage<UserMap>
     */
    IPage<UserMap> findUserMaps(QueryRequest request, UserMap userMap);

    /**
     * 查询（所有）
     *
     * @param userMap userMap
     * @return List<UserMap>
     */
    List<UserMap> findUserMaps(UserMap userMap);

    /**
     * 新增
     *
     * @param userMap userMap
     */
    void createUserMap(UserMap userMap);

    /**
     * 修改
     *
     * @param userMap userMap
     */
    void updateUserMap(UserMap userMap);

    /**
     * 删除
     *
     * @param userMap userMap
     */
    void deleteUserMap(UserMap userMap);
}
