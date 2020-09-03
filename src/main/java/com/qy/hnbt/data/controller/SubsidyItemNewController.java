package com.qy.hnbt.data.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qy.hnbt.data.common.AreaEnum;
import com.qy.hnbt.data.common.CommonResult;
import com.qy.hnbt.data.utils.AccountCheckInfo;
import com.qy.hnbt.data.utils.AccountUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.qy.hnbt.data.entity.SubsidyItemNew;
import com.qy.hnbt.data.service.ISubsidyItemNewService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@RequestMapping("/data/subsidy-item-new")
public class SubsidyItemNewController {
    @Autowired
    ISubsidyItemNewService subsidyItemNewService;

    @ApiOperation("get SubsidyItemNew")
    @GetMapping("getSubsidyItemNew")
    public CommonResult getSubsidyItemNew(Long id){
        CommonResult<SubsidyItemNew> commonResult;
        SubsidyItemNew subsidyItemNew = subsidyItemNewService.getById(id);
        commonResult = subsidyItemNew == null ? CommonResult.failed() : CommonResult.success(subsidyItemNew);
        return commonResult;
    }



    @ApiOperation("SubsidyItemNew list")
    @GetMapping("listSubsidyItemNew")
    public CommonResult listSubsidyItemNew(){
        CommonResult<List<SubsidyItemNew>> commonResult;
        List<SubsidyItemNew> subsidyItemNews = subsidyItemNewService.list(new QueryWrapper<SubsidyItemNew>().select("money","showing_status"));
        commonResult = subsidyItemNews == null ? CommonResult.failed() : CommonResult.success(subsidyItemNews);
        return commonResult;
    }

    @ApiOperation("SubsidyItemNew Statistics")
    @GetMapping("statistics")
    public CommonResult statistics(){
        List<SubsidyItemNew> subsidyItemNews = subsidyItemNewService.list(new QueryWrapper<SubsidyItemNew>().select("money","showing_status"));
        BigDecimal result = subsidyItemNews.stream().filter(x->"0000".equals(x.getShowingStatus())).map(SubsidyItemNew::getMoney).reduce(BigDecimal.ZERO,BigDecimal::add);
        return CommonResult.success(result);
    }


    @ApiOperation("save SubsidyItemNew")
    @PostMapping("save")
    public CommonResult saveSubsidyItemNew(SubsidyItemNew subsidyItemNew){
        CommonResult<String> commonResult;
        boolean result = subsidyItemNewService.save(subsidyItemNew);
        commonResult = result ? CommonResult.success("插入成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("update SubsidyItemNew")
    @PostMapping("update")
    public CommonResult updateSubsidyItemNew(SubsidyItemNew subsidyItemNew){
        CommonResult<String> commonResult;
        boolean result = subsidyItemNewService.updateById(subsidyItemNew);
        commonResult = result ? CommonResult.success("修改成功") : CommonResult.failed();
        return commonResult;
    }

    @ApiOperation("delete SubsidyItemNew")
    @PostMapping("delete")
    public CommonResult deleteSubsidyItemNew(Long id){
        CommonResult<String> commonResult;
        boolean result = subsidyItemNewService.removeById(id);
        commonResult = result ? CommonResult.success("删除成功") : CommonResult.failed();
        return commonResult;
    } 
    @ApiOperation("page SubsidyItemNew list")
    @PostMapping("/page")
    public CommonResult<IPage> page(@RequestParam(value = "pageNum", defaultValue = "1")
        @ApiParam("页码") Integer pageNum,
        @RequestParam(value = "pageSize", defaultValue = "3")
        @ApiParam("每页数量") Integer pageSize) {
        IPage<SubsidyItemNew> page = new Page<>(pageNum,pageSize);
        IPage<SubsidyItemNew> resultPage = subsidyItemNewService.page(page);
        return CommonResult.success(resultPage);
    }


    @ApiOperation("matchBankSetId")
    @PostMapping("matchBankSetId")
    public CommonResult matchBankSetId(HttpServletRequest request){
        Integer count = subsidyItemNewService.count();
        Integer current = 1;
        Integer size = 500;

        while(current * size < count){
            IPage<SubsidyItemNew> subsidyItemNewIPage = subsidyItemNewService.page(new Page<>(current,size),new QueryWrapper<SubsidyItemNew>().isNull("bank_set_id").select("id","receiver_account_no","receiver_bank_code"));
            List<SubsidyItemNew> list = subsidyItemNewIPage.getRecords();
            list.forEach(subsidy -> {
                AccountCheckInfo accountCheckInfo = new AccountCheckInfo(subsidy.getReceiverAccountNo(),null);
                if(accountCheckInfo.isCard()){
                    subsidy.setBankSetId(accountCheckInfo.getBankSetId().toString());
                    subsidyItemNewService.updateById(subsidy);
                }
            });
            log.info("更新了{}条",current * size);
            current ++ ;
        }


        return CommonResult.success("success");
    }
    @ApiOperation("matchBankSetIdNoCard")
    @PostMapping("matchBankSetIdNoCard")
    public CommonResult matchBankSetIdNoCard(){
        int count = subsidyItemNewService.count(new QueryWrapper<SubsidyItemNew>().isNull("bank_set_id"));
        Integer current = 0;
        Integer size = 500;
        List<SubsidyItemNew> subsidyItemNews = new ArrayList<>();

        while(current * size < count){
            IPage<SubsidyItemNew> subsidyItemNewPage = subsidyItemNewService.page(new Page<>(current,size),new QueryWrapper<SubsidyItemNew>().isNull("bank_set_id").select("id","receiver_account_no","receiver_bank_code","bank_set_id").orderBy(false,false,"id"));
            List<SubsidyItemNew> list = subsidyItemNewPage.getRecords();
            list.forEach(subsidyItemNew -> {
                AccountCheckInfo accountCheckInfo = new AccountCheckInfo(subsidyItemNew.getReceiverAccountNo(),subsidyItemNew.getReceiverBankCode());
                if(accountCheckInfo.isPass()){
                    subsidyItemNew.setBankSetId(accountCheckInfo.getBankSetId().toString());
                    subsidyItemNews.add(subsidyItemNew);
                }
            });

            log.info("加载了{}条",current * size);
            count = (int) subsidyItemNewPage.getTotal();
            current ++ ;
        }
        if(subsidyItemNews.size() >= 1000){
            for (int i = 0; i*size < subsidyItemNews.size(); i++) {
                List<SubsidyItemNew> subsidyItemNewList =subsidyItemNews.subList(i * size,(i+1) * size);
                subsidyItemNewService.updateBatchById(subsidyItemNewList);
                log.info("更新了{}条",i * size);
            }
        }else {
            subsidyItemNewService.updateBatchById(subsidyItemNews);
            log.info("更新了{}条", subsidyItemNews.size());
        }
        return CommonResult.success("success");
    }

    @ApiOperation("setDataSource")
    @PostMapping("setDataSource")
    public CommonResult setDataSource(HttpServletRequest request,String key){
        if("".equals(key) || key == null){
            request.getSession().setAttribute("tenantName", AreaEnum.NAPO.getKey());
        }else{
            request.getSession().setAttribute("tenantName",AreaEnum.valueOf(key).getKey());
        }
        return CommonResult.success(key);
    }

}

