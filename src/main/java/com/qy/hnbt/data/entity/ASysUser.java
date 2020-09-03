package com.qy.hnbt.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 用户
 * </p>
 *
 * @author qy
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="ASysUser对象", description="用户")
public class ASysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "密码盐值")
    private String salt;

    @ApiModelProperty(value = "是否可用")
    private Integer state;

    @ApiModelProperty(value = "机构id")
    private Long orgId;

    @ApiModelProperty(value = "部门id")
    private Long departId;

    private Long roleId;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "身份证")
    private String idn;

    @ApiModelProperty(value = "联系地址")
    private String addr;

    @ApiModelProperty(value = "备注")
    private String remark;

    private Long ukeyId;

    private Integer passwordFlag;

    private String userNo;

    @ApiModelProperty(value = "重置后的随机密码")
    private String tPassword;

    @ApiModelProperty(value = "地区")
    private String areaCode;


}
