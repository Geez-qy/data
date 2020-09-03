package com.qy.hnbt.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author qy
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="SubsidyItemNew对象", description="")
public class SubsidyItemNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "收款账户名")
    private String receiverAccountName;

    @ApiModelProperty(value = "收款账户号")
    private String receiverAccountNo;

    @ApiModelProperty(value = "收款银行名称")
    private String receiverBankName;

    @ApiModelProperty(value = "收款银行行号")
    private String receiverBankCode;

    private String receiverIdn;

    @ApiModelProperty(value = "交易金额（单位：元）")
    private BigDecimal money;

    @ApiModelProperty(value = "交易金额（单位：分）")
    private Long tranAmt;

    @ApiModelProperty(value = "主表id")
    private String mainId;

    @ApiModelProperty(value = "交易流水号")
    private String tranId;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime tranTime;

    @ApiModelProperty(value = "交易状态")
    private String tranStatus;

    @ApiModelProperty(value = "银联方交易流水号")
    private String ylNo;

    @ApiModelProperty(value = "对账状态")
    private String verifyStatus;

    @ApiModelProperty(value = "对账时间")
    private LocalDateTime verifyTime;

    @ApiModelProperty(value = "对账金额")
    private Double verifyMoney;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态1审核中2待支付中3支付中4已支付5已对账6支付失败7支付失败已对账9支付异常")
    private Integer status;

    @ApiModelProperty(value = "用于显示的状态")
    private String showingStatus;

    @ApiModelProperty(value = "当前交易信息")
    private String msg;

    @ApiModelProperty(value = "交易信息的级别")
    private Integer codeLevel;

    @ApiModelProperty(value = "交易信息变更的时间")
    private LocalDateTime msgTime;

    @ApiModelProperty(value = "地址")
    private String addr;

    @ApiModelProperty(value = "联系方式")
    private String contactDetail;

    @ApiModelProperty(value = "一卡通系统内银行id")
    private String bankSetId;


}
