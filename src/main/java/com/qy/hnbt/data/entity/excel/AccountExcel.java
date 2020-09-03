package com.qy.hnbt.data.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountExcel extends BaseRowModel implements Serializable {
    @ExcelProperty(index = 0)
    private String accountNo;
    @ExcelProperty(index = 1)
    private String accountName;
    @ExcelProperty(index = 2)
    private String bankNo;
    @ExcelProperty(index = 3)
    private String bankName;
    @ExcelProperty(index = 4)
    private String departName;
    @ExcelProperty(index = 5)
    private String areaCode;
}
