package cc.mrbird.febs.hama.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户表 Entity
 *
 * @author MrBird
 * @date 2020-08-09 19:51:09
 */
@Data
@TableName("h_user")
public class User {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 身份证号
     */
    @TableField("user_card_no")
    private String userCardNo;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 手机号
     */
    @TableField("iphone")
    private String iphone;

    /**
     * 一级密码
     */
    @TableField("fist_pwd")
    private String fistPwd;

    /**
     * 二级密码
     */
    @TableField("second_pwd")
    private String secondPwd;

    /**
     * 邀请人id
     */
    @TableField("from_id")
    private Long fromId;

    /**
     * 邀请人手机号
     */
    @TableField("from_iphone")
    private String fromIphone;

    /**
     * 区县ID
     */
    @TableField("area_id")
    private Long areaId;

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
