package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.UserPayInfo;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:51
 */
public interface IUserPayInfoService extends IService<UserPayInfo> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param userPayInfo userPayInfo
     * @return IPage<UserPayInfo>
     */
    IPage<UserPayInfo> findUserPayInfos(QueryRequest request, UserPayInfo userPayInfo);

    /**
     * 查询（所有）
     *
     * @param userPayInfo userPayInfo
     * @return List<UserPayInfo>
     */
    List<UserPayInfo> findUserPayInfos(UserPayInfo userPayInfo);

    /**
     * 新增
     *
     * @param userPayInfo userPayInfo
     */
    void createUserPayInfo(UserPayInfo userPayInfo);

    /**
     * 修改
     *
     * @param userPayInfo userPayInfo
     */
    void updateUserPayInfo(UserPayInfo userPayInfo);

    /**
     * 删除
     *
     * @param userPayInfo userPayInfo
     */
    void deleteUserPayInfo(UserPayInfo userPayInfo);
}
