package cc.mrbird.febs.hama.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2020-08-09 19:51:53
 */
@Data
@TableName("h_order")
public class Order {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 0未支付 1已支付 2取消
     */
    @TableField("order_status")
    private Byte orderStatus;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 支付时间
     */
    @TableField("pay_time")
    private Date payTime;

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
