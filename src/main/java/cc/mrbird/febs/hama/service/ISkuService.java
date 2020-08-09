package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.Sku;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:25
 */
public interface ISkuService extends IService<Sku> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param sku sku
     * @return IPage<Sku>
     */
    IPage<Sku> findSkus(QueryRequest request, Sku sku);

    /**
     * 查询（所有）
     *
     * @param sku sku
     * @return List<Sku>
     */
    List<Sku> findSkus(Sku sku);

    /**
     * 新增
     *
     * @param sku sku
     */
    void createSku(Sku sku);

    /**
     * 修改
     *
     * @param sku sku
     */
    void updateSku(Sku sku);

    /**
     * 删除
     *
     * @param sku sku
     */
    void deleteSku(Sku sku);
}
