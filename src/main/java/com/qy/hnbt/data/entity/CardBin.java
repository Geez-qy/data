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
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CardBin对象", description="")
public class CardBin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String binCode;

    private String cardName;

    @ApiModelProperty(value = "bank_setid")
    private Long bankSetId;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "发卡行名称及机构代码")
    private String owner;

    @ApiModelProperty(value = "卡长度")
    private Integer length;

    @ApiModelProperty(value = "卡类型")
    private String type;

    @ApiModelProperty(value = "bin号长度")
    private Integer binLength;


}
