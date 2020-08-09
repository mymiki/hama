package cc.mrbird.febs.hama.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 执行计划中胡商品列表 Entity
 *
 * @author MrBird
 * @date 2020-08-09 19:51:14
 */
@Data
@TableName("h_on_salce_task_map")
public class OnSalceTaskMap {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务计划id
     */
    @TableField("on_sale_task_id")
    private Long onSaleTaskId;

    /**
     * spu_id
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * sku_id
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 增加时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 增加人id
     */
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新人id
     */
    @TableField("update_user_id")
    private Long updateUserId;

    /**
     * 是否删除 :0否 1是
     */
    @TableField("delete_flag")
    private Byte deleteFlag;

}
