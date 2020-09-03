package com.qy.hnbt.data.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qy.hnbt.data.common.CommonResult;

import com.qy.hnbt.data.entity.PayBank;
import com.qy.hnbt.data.service.IPayBankService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
* <p>
    *  前端控制器
    * </p>
*
* @author qy
* @since 2020-08-24
*/
@Api
@RestController
@RequestMapping("/data/pay-bank")
public class PayBankController {
    @Autowired
    IPayBankService payBankService;

    @ApiOperation("get PayBank")
    @GetMapping("getPayBank")
    public CommonResult getPayBank(Long id){
        CommonResult<PayBank> commonResult;
        PayBank payBank = payBankService.getById(id);
        commonResult = payBank == null ? CommonResult.failed() : CommonResult.success(payBank);
        return commonResult;
    }



    @ApiOperation("PayBank list")
    @GetMapping("listPayBank")
    public CommonResult listPayBank(){
        CommonResult<List<PayBank>> commonResult;
        List<PayBank> payBanks = payBankService.list();
        commonResult = payBanks == null ? CommonResult.failed() : CommonResult.success(payBanks);
        return commonResult;
    }


    @ApiOperation("save PayBank")
    @PostMapping("save")
    public CommonResult savePayBank(PayBank payBank){
        CommonResult<String> commonResult;
        boolean result = payBankService.save(payBank);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update PayBank")
    @PostMapping("update")
    public CommonResult updatePayBank(PayBank payBank){
        CommonResult<String> commonResult;
        boolean result = payBankService.updateById(payBank);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete PayBank")
    @PostMapping("delete")
    public CommonResult deletePayBank(Long id){
        CommonResult<String> commonResult;
        boolean result = payBankService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page PayBank list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<PayBank> page = new Page<>(pageNum,pageSize);
        IPage<PayBank> resultPage = payBankService.page(page);
        return CommonResult.success(resultPage);
    }


}

