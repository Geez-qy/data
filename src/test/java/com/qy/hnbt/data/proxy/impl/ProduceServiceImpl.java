package com.qy.hnbt.data.proxy.impl;

import com.qy.hnbt.data.proxy.ProduceService;

public class ProduceServiceImpl implements ProduceService {
    @Override
    public void produce(String name) {
        System.out.println("produce " + name);
    }
}
