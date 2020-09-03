package com.qy.hnbt.data.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class SubsidyylResult extends BaseRowModel {
    @ExcelProperty(value = "商户号",index = 1)
    private String  merNo;
    @ExcelProperty(value = "工作日",index = 2)
    private String  workDay;
    @ExcelProperty(value = "商户账号",index = 4)
    private String  accountNo;
    @ExcelProperty(value = "商户户名",index = 5)
    private String  accountName;
    @ExcelProperty(value = "金额",index = 7)
    private String  money;
    @ExcelProperty(value = "银行反馈提示",index = 12)
    private String  bankTip;
    @ExcelProperty(value = "放大镜结果",index = 13)
    private String  detailResult;
    @ExcelProperty(value = "备注",index = 17)
    private String  remarks;
    @ExcelProperty(index = 18)
    private String  tranId;



}
