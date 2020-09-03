package com.qy.hnbt.data.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qy.hnbt.data.common.CommonResult;
import com.qy.hnbt.data.entity.ASysDepart;
import com.qy.hnbt.data.entity.ASysUser;
import com.qy.hnbt.data.service.IASysDepartService;
import com.qy.hnbt.data.service.IASysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
* <p>
    * 用户 前端控制器
    * </p>
*
* @author qy
* @since 2020-08-12
*/
@Api
@RestController
@RequestMapping("/hnbt/a-sys-user")
public class ASysUserController {
    @Autowired
    IASysUserService aSysUserService;
    @Autowired
    IASysDepartService departService;

    private static final String PWD = "FAFF242B31CA558C172982D67323B44F";
    private static final String SALT = "1111";

    @ApiOperation("get ASysUser")
    @GetMapping("getASysUser")
    public CommonResult getASysUser(Long id){
        CommonResult<ASysUser> commonResult;
        ASysUser aSysUser = aSysUserService.getById(id);
        commonResult = aSysUser == null ? CommonResult.failed() : CommonResult.success(aSysUser);
        return commonResult;
    }



    @ApiOperation("ASysUser list")
    @GetMapping("listASysUser")
    public CommonResult listASysUser(){
        CommonResult<List<ASysUser>> commonResult;
        List<ASysUser> aSysUsers = aSysUserService.list();
        commonResult = aSysUsers == null ? CommonResult.failed() : CommonResult.success(aSysUsers);
        return commonResult;
    }


    @ApiOperation("save ASysUser")
    @PostMapping("save")
    public CommonResult saveASysUser(ASysUser aSysUser){
        CommonResult<String> commonResult;
        boolean result = aSysUserService.save(aSysUser);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update ASysUser")
    @PostMapping("update")
    public CommonResult updateASysUser(ASysUser aSysUser){
        CommonResult<String> commonResult;
        boolean result = aSysUserService.updateById(aSysUser);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete ASysUser")
    @PostMapping("delete")
    public CommonResult deleteASysUser(Long id){
        CommonResult<String> commonResult;
        boolean result = aSysUserService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page ASysUser list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<ASysUser> page = new Page<>(pageNum,pageSize);
        IPage<ASysUser> resultPage = aSysUserService.page(page);
        return CommonResult.success(resultPage);
    }

    @ApiOperation("generateUser")
    @PostMapping(value = "generateUser")
    public CommonResult generateUserByExcel(Integer departId,Integer userNo)  {
        ASysDepart depart = departService.getById(departId);
        Optional<ASysDepart> optional = Optional.of(depart);
        QueryWrapper<ASysUser> wrapper = new QueryWrapper<>();
        if (!optional.isPresent()){
            return CommonResult.failed("没有这个部门");
        }
        wrapper.eq("depart_id",depart.getId());
        List<ASysUser> users = aSysUserService.list(wrapper);
        String deptName = depart.getAbridgeName();
        if(users.isEmpty()){
            ASysUser user = new ASysUser(null,depart.getAbridgeName(),null,PWD,SALT,1,null,depart.getId(),null, LocalDateTime.now(),null,null,null,null,null,null,null,"11111",depart.getAreaCode());
            for(Integer roleId = 25;roleId <= 30;roleId ++ ){
                user.setUsername(deptName +"0"+ (roleId-24));
                user.setRoleId(Long.valueOf(roleId));
                user.setUserNo("000" + userNo++);
                aSysUserService.save(user);
            }
        }
        return CommonResult.success("已生成"+depart.getName()+"下的用户");
    }

}

