package com.caizhen.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author wujiangbo
 * @since 2021-12-21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cai_log")
public class Log {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 模块标题
     */
    private String title;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;
    /**
     * 操作人员
     */
    @TableField("oper_name")
    private String operName;
    /**
     * 请求URL
     */
    @TableField("request_url")
    private String requestUrl;
    /**
     * 请求IP地址
     */
    private String ip;
    /**
     * IP归属地
     */
    @TableField("ip_location")
    private String ipLocation;
    /**
     * 请求参数
     */
    @TableField("request_param")
    private String requestParam;
    /**
     * 方法响应参数
     */
    @TableField("response_result")
    private String responseResult;
    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;
    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;
    /**
     * 操作时间
     */
    @TableField("oper_time")
    private Date operTime;
    /**
     * 方法执行耗时（单位：毫秒）
     */
    @TableField("take_time")
    private Long takeTime;

}


