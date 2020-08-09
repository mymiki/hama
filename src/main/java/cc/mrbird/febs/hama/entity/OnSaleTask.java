package cc.mrbird.febs.hama.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 上架任务执行计划表 Entity
 *
 * @author MrBird
 * @date 2020-08-09 19:51:16
 */
@Data
@TableName("h_on_sale_task")
public class OnSaleTask {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 开始小时数:如:13 ->13:00
     */
    @TableField("begin_time_hour")
    private Byte beginTimeHour;

    /**
     * 结束小时数:如:20 ->20:00
     */
    @TableField("end_time_hour")
    private Byte endTimeHour;

    /**
     * 开始日期
     */
    @TableField("begin_date")
    private Date beginDate;

    /**
     * 持续天数:如:365天（1年）
     */
    @TableField("last_days")
    private Byte lastDays;

    /**
     * 状态：0停止 1启用
     */
    @TableField("task_staus")
    private Byte taskStaus;

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
