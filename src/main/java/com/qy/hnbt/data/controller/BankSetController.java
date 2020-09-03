package com.qy.hnbt.data.controller;

import com.qy.hnbt.data.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.qy.hnbt.data.entity.BankSet;
import com.qy.hnbt.data.service.IBankSetService;
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
@RequestMapping("/data/bank-set")
public class BankSetController {
    @Autowired
    IBankSetService bankSetService;

    @ApiOperation("get BankSet")
    @GetMapping("getBankSet")
    public CommonResult getBankSet(Long id){
        CommonResult<BankSet> commonResult;
        BankSet bankSet = bankSetService.getById(id);
        commonResult = bankSet == null ? CommonResult.failed() : CommonResult.success(bankSet);
        return commonResult;
    }



    @ApiOperation("BankSet list")
    @GetMapping("listBankSet")
    public CommonResult listBankSet(){
        CommonResult<List<BankSet>> commonResult;
        List<BankSet> bankSets = bankSetService.list();
        commonResult = bankSets == null ? CommonResult.failed() : CommonResult.success(bankSets);
        return commonResult;
    }


    @ApiOperation("save BankSet")
    @PostMapping("save")
    public CommonResult saveBankSet(BankSet bankSet){
        CommonResult<String> commonResult;
        boolean result = bankSetService.save(bankSet);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update BankSet")
    @PostMapping("update")
    public CommonResult updateBankSet(BankSet bankSet){
        CommonResult<String> commonResult;
        boolean result = bankSetService.updateById(bankSet);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete BankSet")
    @PostMapping("delete")
    public CommonResult deleteBankSet(Long id){
        CommonResult<String> commonResult;
        boolean result = bankSetService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page BankSet list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<BankSet> page = new Page<>(pageNum,pageSize);
        IPage<BankSet> resultPage = bankSetService.page(page);
        return CommonResult.success(resultPage);
    }


}

