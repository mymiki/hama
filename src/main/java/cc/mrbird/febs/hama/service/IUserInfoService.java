package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.UserInfo;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表 Service接口
 *
 * @author MrBird
 * @date 2020-08-10 22:31:04
 */
public interface IUserInfoService extends IService<UserInfo> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param userInfo userInfo
     * @return IPage<UserInfo>
     */
    IPage<UserInfo> findUserInfos(QueryRequest request, UserInfo userInfo);

    /**
     * 查询（所有）
     *
     * @param userInfo userInfo
     * @return List<UserInfo>
     */
    List<UserInfo> findUserInfos(UserInfo userInfo);

    /**
     * 新增
     *
     * @param userInfo userInfo
     */
    void createUserInfo(UserInfo userInfo);

    /**
     * 修改
     *
     * @param userInfo userInfo
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 删除
     *
     * @param userInfo userInfo
     */
    void deleteUserInfo(UserInfo userInfo);
}
