package com.qy.hnbt.data.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardExcel extends BaseRowModel {
    @ExcelProperty(value = "银行名称",index = 0)
    private String bankName;
    @ExcelProperty(value = "卡值",index = 13)
    private String value;
    @ExcelProperty(value = "长度",index = 8)
    private Integer length;
    @ExcelProperty(value = "类型",index = 15)
    private String type;
}
