package com.qy.hnbt.data.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qy.hnbt.data.common.CommonResult;

import com.qy.hnbt.data.entity.TranItemResponse;
import com.qy.hnbt.data.service.ITranItemResponseService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
* <p>
    *  前端控制器
    * </p>
*
* @author qy
* @since 2020-08-26
*/
@Api
@RestController
@RequestMapping("/data/tran-item-response")
public class TranItemResponseController {
    @Autowired
    ITranItemResponseService tranItemResponseService;

    @ApiOperation("get TranItemResponse")
    @GetMapping("getTranItemResponse")
    public CommonResult getTranItemResponse(Long id){
        CommonResult<TranItemResponse> commonResult;
        TranItemResponse tranItemResponse = tranItemResponseService.getById(id);
        commonResult = tranItemResponse == null ? CommonResult.failed() : CommonResult.success(tranItemResponse);
        return commonResult;
    }



    @ApiOperation("TranItemResponse list")
    @GetMapping("listTranItemResponse")
    public CommonResult listTranItemResponse(){
        CommonResult<List<TranItemResponse>> commonResult;
        List<TranItemResponse> tranItemResponses = tranItemResponseService.list();
        commonResult = tranItemResponses == null ? CommonResult.failed() : CommonResult.success(tranItemResponses);
        return commonResult;
    }


    @ApiOperation("save TranItemResponse")
    @PostMapping("save")
    public CommonResult saveTranItemResponse(TranItemResponse tranItemResponse){
        CommonResult<String> commonResult;
        boolean result = tranItemResponseService.save(tranItemResponse);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update TranItemResponse")
    @PostMapping("update")
    public CommonResult updateTranItemResponse(TranItemResponse tranItemResponse){
        CommonResult<String> commonResult;
        boolean result = tranItemResponseService.updateById(tranItemResponse);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete TranItemResponse")
    @PostMapping("delete")
    public CommonResult deleteTranItemResponse(Long id){
        CommonResult<String> commonResult;
        boolean result = tranItemResponseService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page TranItemResponse list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<TranItemResponse> page = new Page<>(pageNum,pageSize);
        IPage<TranItemResponse> resultPage = tranItemResponseService.page(page);
        return CommonResult.success(resultPage);
    }


}

