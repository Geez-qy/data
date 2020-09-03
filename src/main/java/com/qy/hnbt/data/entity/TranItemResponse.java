package com.qy.hnbt.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qy
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TranItemResponse对象", description="")
public class TranItemResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易号")
    private String tranId;

    @ApiModelProperty(value = "批次号")
    private String batchId;

    @ApiModelProperty(value = "交易码")
    private String code;

    @ApiModelProperty(value = "交易信息")
    private String msg;

    @ApiModelProperty(value = "原始交易信息")
    private String baseMsg;

    @ApiModelProperty(value = "交易码等级")
    private Integer codeLevel;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "原始交易码")
    private String baseCode;

    @ApiModelProperty(value = "请求时间")
    private Long responseTime;


}
