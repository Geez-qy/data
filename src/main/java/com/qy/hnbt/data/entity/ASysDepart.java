package com.qy.hnbt.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author qy
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="ASysDepart对象", description="部门")
public class ASysDepart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "机构")
    private Long orgId;

    @ApiModelProperty(value = "状态@url:../sysCode/getCodeList.do?type=status_usable")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "编号代码")
    private String code;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "补贴代发放部门")
    @TableField("deptList")
    private String deptList;

    private String orgStr;

    @ApiModelProperty(value = "部门角色:1.业务部门；2.补贴代发放部门")
    @TableField("deptRole")
    private Integer deptRole;

    private Long fatherId;

    private Integer type;

    private String areaCode;

    @ApiModelProperty(value = "部门缩写")
    private String abridgeName;

    @ApiModelProperty(value = "部门一些特殊属性，目前只有金融机构用户在使用，该字段表示金融机构用户在系统内的银行行号id")
    private String attr;

}
