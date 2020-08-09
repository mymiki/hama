package cc.mrbird.febs.hama.service;

import cc.mrbird.febs.hama.entity.Spu;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2020-08-09 19:51:12
 */
public interface ISpuService extends IService<Spu> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param spu spu
     * @return IPage<Spu>
     */
    IPage<Spu> findSpus(QueryRequest request, Spu spu);

    /**
     * 查询（所有）
     *
     * @param spu spu
     * @return List<Spu>
     */
    List<Spu> findSpus(Spu spu);

    /**
     * 新增
     *
     * @param spu spu
     */
    void createSpu(Spu spu);

    /**
     * 修改
     *
     * @param spu spu
     */
    void updateSpu(Spu spu);

    /**
     * 删除
     *
     * @param spu spu
     */
    void deleteSpu(Spu spu);
}
