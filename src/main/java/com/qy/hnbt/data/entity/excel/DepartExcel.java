package com.qy.hnbt.data.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class DepartExcel extends BaseRowModel {
    @ExcelProperty(value = "部门名称",index = 0)
    private String name;
    @ExcelProperty(value = "部门缩写",index = 1)
    private String abbreviation;
    @ExcelProperty(value = "区县编码",index = 2)
    private String areacode;
    @ExcelProperty(value = "部门编码",index = 3)
    private String code;

}
