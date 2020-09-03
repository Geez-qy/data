package com.qy.hnbt.data.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.qy.hnbt.data.common.CommonResult;
import com.qy.hnbt.data.entity.ASysDepart;
import com.qy.hnbt.data.entity.excel.DepartExcel;
import com.qy.hnbt.data.service.IASysDepartService;
import com.qy.hnbt.data.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* <p>
    * 部门 前端控制器
    * </p>
*
* @author qy
* @since 2020-08-07
*/
@Api
@RestController
@RequestMapping("/hnbt/a-sys-depart")
public class ASysDepartController {
    @Autowired
    IASysDepartService aSysDepartService;

    @ApiOperation("get ASysDepart")
    @GetMapping("getASysDepart")
    public CommonResult getASysDepart(Long id){
        CommonResult<ASysDepart> commonResult;
        ASysDepart aSysDepart = aSysDepartService.getById(id);
        commonResult = aSysDepart == null ? CommonResult.failed() : CommonResult.success(aSysDepart);
        return commonResult;
    }



    @ApiOperation("ASysDepart list")
    @GetMapping("listASysDepart")
    public CommonResult listASysDepart(){
        CommonResult<List<ASysDepart>> commonResult;
        List<ASysDepart> aSysDeparts = aSysDepartService.list();
        commonResult = aSysDeparts == null ? CommonResult.failed() : CommonResult.success(aSysDeparts);
        return commonResult;
    }


    @ApiOperation("save ASysDepart")
    @PostMapping("save")
    public CommonResult saveASysDepart(ASysDepart aSysDepart){
        CommonResult<String> commonResult;
        boolean result = aSysDepartService.save(aSysDepart);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update ASysDepart")
    @PostMapping("update")
    public CommonResult updateASysDepart(ASysDepart aSysDepart){
        CommonResult<String> commonResult;
        boolean result = aSysDepartService.updateById(aSysDepart);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete ASysDepart")
    @PostMapping("delete")
    public CommonResult deleteASysDepart(Long id){
        CommonResult<String> commonResult;
        boolean result = aSysDepartService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page ASysDepart list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<ASysDepart> page = new Page<>(pageNum,pageSize);
        IPage<ASysDepart> resultPage = aSysDepartService.page(page);
        return CommonResult.success(resultPage);
    }


//    @ApiOperation("批量导入部门")
//    @PostMapping(value = "importDepart",headers = "content-type=multipart/form-data")
//    public CommonResult importDepart(@ApiParam(value = "depart excel",required = true) MultipartFile excel) throws Exception {
//        Object objList = ExcelUtils.readExcel(excel, new DepartExcel(), 1, 1);
//        if (objList == null) {
//            return CommonResult.failed("导入的数据不能为空");
//        }
//
//        List<DepartExcel> orderList = (List<DepartExcel>) objList;
//
//        if (orderList == null || orderList.size() <= 0) {
//            return CommonResult.failed( "导入的数据不能为空");
//        }
//        for (DepartExcel departExcel : orderList){
//            ASysDepart depart = new ASysDepart(null,departExcel.getName(),null,1,null,departExcel.getCode(),null,null,"-1",null,null,null,5,departExcel.getAreacode(),departExcel.getAbbreviation());
//            aSysDepartService.save(depart);
//        }
//        return CommonResult.success("导入成功");
//    }
}

