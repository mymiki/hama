package cc.mrbird.febs.hama.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2020-08-09 19:51:25
 */
@Data
@TableName("h_sku")
public class Sku {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu_id
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 成本价
     */
    @TableField("cost_price")
    private BigDecimal costPrice;
    /**
     * 售价
     */
    @TableField("sale_price")
    private BigDecimal salePrice;
    /**
     * 展示用价（原价）
     */
    @TableField("show_price")
    private BigDecimal showPrice;
    /**
     * 最低价
     */
    @TableField("lower_price")
    private BigDecimal lowerPrice;
    /**
     * 最高价
     */
    @TableField("upper_price")
    private BigDecimal upperPrice;
    /**
     * 消耗购买票券数量
     */
    @TableField("cost_ticket_count")
    private Integer costTicketCount;

    /**
     * 是否产生收益 0否 1是
     */
    @TableField("is_income")
    private Byte isIncome;

    /**
     * 收益比率: 10.25 -> 10.25%/1天
     */
    @TableField("income_rate")
    private BigDecimal incomeRate;
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
