package com.qy.hnbt.data.code;

import com.qy.hnbt.data.entity.SubsidyItemNew;

import java.math.BigDecimal;
import java.util.Optional;

public class SubsidyTest {
    public static void main(String[] args) {
        SubsidyItemNew subsidy = new SubsidyItemNew();
        SubsidyItemNew subsidy1 = new SubsidyItemNew();
        subsidy.setAddr("广西");
        subsidy.setId(1L);
        subsidy.setMoney(new BigDecimal(888));
        System.out.println(getSubsidy(subsidy));
        System.out.println(getSubsidy(subsidy1));
    }

    public static String getSubsidy(SubsidyItemNew subsidyItemNew){
        return Optional.ofNullable(subsidyItemNew)
                .map(SubsidyItemNew::getAddr)
                .orElseThrow(() -> new IllegalArgumentException(" 无法获取到新的值")).toUpperCase();
    }
}
