package com.qy.hnbt.data.utils;


import com.qy.hnbt.data.entity.CardBin;
import com.qy.hnbt.data.entity.PayBank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountCheckInfo {
    // 一卡通系统内银行Id
    private Integer bankSetId;
    // 是否是银行卡
    private boolean isCard;
    // 银行名称（限存折）
    private String bankName;
    // 银行行号（限存折）
    private String bankCode;
    // 错误信息（限失败时）
    private String errMsg;
    // 是否通过验证
    private boolean isPass;
    // 账号信息
    private String accountNo;
    // 银行卡卡名称（限银行卡）
    private String cardName;
    // 银行卡类型（借记卡、贷记卡等）
    private String cardType;

    public AccountCheckInfo(String accountNo, Object bankCode) {
        this.accountNo = accountNo;
        CardBin cardInfo = AccountUtil.getCardInfo(this.accountNo);
        if (cardInfo == null) {
            this.isCard = false;
            this.bankCode = bankCode == null ? null : bankCode.toString();
            if (this.bankCode == null || "".equals(StringUtil.trim(this.bankCode))) {
                this.isPass = false;
                this.errMsg = "存折必须输入银行行号";
                this.bankName = "所属银行未知";
            } else {
                PayBank czInfo = AccountUtil.getCZInfo(this.bankCode);
                this.isPass = true;
                if (czInfo == null) {
                    this.bankSetId = 99;
                    this.bankName = "其他银行";
                } else {
                    this.bankName = czInfo.getBankName();
                    this.bankSetId = czInfo.getBankSetId().intValue();
                }
            }
        } else {
            this.isCard = true;
            this.bankSetId = cardInfo.getBankSetId().intValue();
            this.bankName = AccountUtil.getBankNameBySetId(this.bankSetId);
            this.cardName = cardInfo.getCardName();
            this.cardType = cardInfo.getType();
            int cardLength = Integer.valueOf(cardInfo.getLength());
            if (this.accountNo.length() == cardLength) {
                this.isPass = true;
            } else {
                this.isPass = false;
                this.errMsg = "卡号长度不正确，正确长度:" + cardLength + ",实际长度:" + this.accountNo.length();
            }
        }
    }
}
