package cc.mrbird.febs.hama.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户资产表 Entity
 *
 * @author MrBird
 * @date 2020-08-09 19:51:39
 */
@Data
@TableName("h_user_capital")
public class UserCapital {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 总资产
     */
    @TableField("total_money")
    private BigDecimal totalMoney;
    /**
     * 推广所得资产
     */
    @TableField("extend_money")
    private BigDecimal extendMoney;
    /**
     * 合约收益
     */
    @TableField("contract_money")
    private BigDecimal contractMoney;
    /**
     * 消费用的票
     */
    @TableField("ticket_count")
    private Integer ticketCount;

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
