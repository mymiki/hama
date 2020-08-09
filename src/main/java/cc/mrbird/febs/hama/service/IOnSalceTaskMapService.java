package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.OnSalceTaskMap;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 执行计划中胡商品列表 Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:14
 */
public interface IOnSalceTaskMapService extends IService<OnSalceTaskMap> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param onSalceTaskMap onSalceTaskMap
     * @return IPage<OnSalceTaskMap>
     */
    IPage<OnSalceTaskMap> findOnSalceTaskMaps(QueryRequest request, OnSalceTaskMap onSalceTaskMap);

    /**
     * 查询（所有）
     *
     * @param onSalceTaskMap onSalceTaskMap
     * @return List<OnSalceTaskMap>
     */
    List<OnSalceTaskMap> findOnSalceTaskMaps(OnSalceTaskMap onSalceTaskMap);

    /**
     * 新增
     *
     * @param onSalceTaskMap onSalceTaskMap
     */
    void createOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap);

    /**
     * 修改
     *
     * @param onSalceTaskMap onSalceTaskMap
     */
    void updateOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap);

    /**
     * 删除
     *
     * @param onSalceTaskMap onSalceTaskMap
     */
    void deleteOnSalceTaskMap(OnSalceTaskMap onSalceTaskMap);
}
