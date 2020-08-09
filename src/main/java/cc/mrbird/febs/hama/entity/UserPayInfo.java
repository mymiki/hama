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
 * @date 2020-08-09 19:51:51
 */
@Data
@TableName("h_user_pay_info")
public class UserPayInfo {

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
     * 账号名称
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 账号类型:0支付宝 1微信 2银行卡
     */
    @TableField("account_type")
    private Byte accountType;

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
