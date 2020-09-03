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
@ApiModel(value="PayBank对象", description="")
public class PayBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String bankName;

    private String bankCode;

    private Long createUserId;

    private LocalDateTime createTime;

    private String areaCode;

    @ApiModelProperty(value = "1正常2已删除")
    private Integer isDelete;

    @ApiModelProperty(value = "bank_setid")
    private Long bankSetId;


}
