package com.qy.hnbt.data.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qy.hnbt.data.entity.BankSet;
import com.qy.hnbt.data.service.IBankSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qy.hnbt.data.common.CommonResult;

import com.qy.hnbt.data.entity.CardBin;
import com.qy.hnbt.data.service.ICardBinService;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.Card;
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
@Log4j2
@RestController
@RequestMapping("/data/card-bin")
public class CardBinController {
    @Autowired
    ICardBinService cardBinService;
    @Autowired
    IBankSetService bankSetService;

    @ApiOperation("get CardBin")
    @GetMapping("getCardBin")
    public CommonResult getCardBin(Long id){
        CommonResult<CardBin> commonResult;
        CardBin cardBin = cardBinService.getById(id);
        commonResult = cardBin == null ? CommonResult.failed() : CommonResult.success(cardBin);
        return commonResult;
    }



    @ApiOperation("CardBin list")
    @GetMapping("listCardBin")
    public CommonResult listCardBin(){
        CommonResult<List<CardBin>> commonResult;
        List<CardBin> cardBins = cardBinService.list();
        commonResult = cardBins == null ? CommonResult.failed() : CommonResult.success(cardBins);
        return commonResult;
    }


    @ApiOperation("save CardBin")
    @PostMapping("save")
    public CommonResult saveCardBin(CardBin cardBin){
        CommonResult<String> commonResult;
        boolean result = cardBinService.save(cardBin);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update CardBin")
    @PostMapping("update")
    public CommonResult updateCardBin(CardBin cardBin){
        CommonResult<String> commonResult;
        boolean result = cardBinService.updateById(cardBin);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete CardBin")
    @PostMapping("delete")
    public CommonResult deleteCardBin(Long id){
        CommonResult<String> commonResult;
        boolean result = cardBinService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page CardBin list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<CardBin> page = new Page<>(pageNum,pageSize);
        IPage<CardBin> resultPage = cardBinService.page(page);
        return CommonResult.success(resultPage);
    }

    @ApiOperation("match bankSetId")
    @PostMapping("matchBankSetId")
    public CommonResult matchBankSetId(){
        List<BankSet> bankSets = bankSetService.list();
        bankSets.forEach(bankSet -> {
            String bankName = bankSet.getName();
            QueryWrapper<CardBin> wrapper = new QueryWrapper<>();
            wrapper.like("owner",bankName);
            cardBinService.list(wrapper).forEach(cardBin -> {
                QueryWrapper<CardBin> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",cardBin.getId());
                cardBin.setBankSetId(bankSet.getId());
                boolean flag = cardBinService.update(cardBin,queryWrapper);
                log.info(flag);
            });
        });
        return CommonResult.success("success");
    }

}

