package com.qy.hnbt.data.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FailedCode {
    FAIL60(60,"销户或不动户"),
    FAIL63(63,"证件信息过期"),
    FAIL66(66,"账号户名不正确"),
    FAIL61(61,"无效卡号或者无此账号"),
    FAIL_EMPTY(null,"账号户名不正确或银行行号不正确");
    private Integer code;
    private String msg;

    public static String getMsg(Integer value){
        for(FailedCode failedCode:values()){
            if(value == null){
                return FAIL_EMPTY.msg;
            }
            if(value.equals(failedCode.getCode())){
                return failedCode.msg;
            }
        }
        return null;
    }
}
