package com.qy.hnbt.data.utils;


import com.qy.hnbt.data.entity.BankSet;
import com.qy.hnbt.data.entity.CardBin;
import com.qy.hnbt.data.entity.PayBank;
import com.qy.hnbt.data.service.IBankSetService;
import com.qy.hnbt.data.service.ICardBinService;
import com.qy.hnbt.data.service.IPayBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountUtil {
    @Autowired
    private IPayBankService payBankService;
    @Autowired
    private IBankSetService bankSetService;
    @Autowired
    private ICardBinService cardBinService;


    private static final Map<String, CardBin> binCodeMap = new HashMap<>();
    private static Map<String, PayBank> bankCodeMap = new HashMap<>();
    private static final Map<Integer, Set<String>> binLengthMap = new HashMap<>();
    private static Map<Long, String> bankSetMap = new HashMap<>();

    @Autowired
    private void initCache() {
        List<CardBin> cardBinList = cardBinService.list();
        List<PayBank> bankCodeList = payBankService.list();
        List<BankSet> bankSetList = bankSetService.list();
        cardBinList.forEach(cardBin -> {
            int binLength = cardBin.getBinLength();
            String binCode = cardBin.getBinCode();
            if (!binLengthMap.containsKey(binLength)) {
                binLengthMap.put(binLength, new HashSet<>());
            }
            binCodeMap.put(binCode, cardBin);
            binLengthMap.get(binLength).add(binCode);
        });

        bankCodeMap = bankCodeList.stream().collect(Collectors.toMap(PayBank::getBankCode,payBank ->payBank));

        bankSetMap = bankSetList.stream().collect(Collectors.toMap(BankSet::getId, BankSet::getName));
    }

    public static PayBank getCZInfo(String bankCode) {
        if (bankCode == null || "".equals(bankCode)) {
            return null;
        }
        if (bankCodeMap.containsKey(bankCode)) {
            return bankCodeMap.get(bankCode);
        }
        return null;
    }

    public static CardBin getCardInfo(String accountNo) {
        if (accountNo == null || "".equals(accountNo)) {
            return null;
        }
        Set<Integer> keyset = binLengthMap.keySet();
        for (Integer integer : keyset) {
            if(accountNo.length() < integer){
                return null;
            }
            String flag = accountNo.substring(0, integer);
            Set<String> binSet = binLengthMap.get(integer);
            if (binSet.contains(flag)) {
                return binCodeMap.get(flag);
            }
        }
        return null;
    }

    public static String getBankNameBySetId(int id) {
        return bankSetMap.getOrDefault((long) id, "其他银行");
    }
}
