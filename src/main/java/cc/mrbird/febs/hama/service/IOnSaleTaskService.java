package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.OnSaleTask;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 上架任务执行计划表 Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:16
 */
public interface IOnSaleTaskService extends IService<OnSaleTask> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param onSaleTask onSaleTask
     * @return IPage<OnSaleTask>
     */
    IPage<OnSaleTask> findOnSaleTasks(QueryRequest request, OnSaleTask onSaleTask);

    /**
     * 查询（所有）
     *
     * @param onSaleTask onSaleTask
     * @return List<OnSaleTask>
     */
    List<OnSaleTask> findOnSaleTasks(OnSaleTask onSaleTask);

    /**
     * 新增
     *
     * @param onSaleTask onSaleTask
     */
    void createOnSaleTask(OnSaleTask onSaleTask);

    /**
     * 修改
     *
     * @param onSaleTask onSaleTask
     */
    void updateOnSaleTask(OnSaleTask onSaleTask);

    /**
     * 删除
     *
     * @param onSaleTask onSaleTask
     */
    void deleteOnSaleTask(OnSaleTask onSaleTask);
}
