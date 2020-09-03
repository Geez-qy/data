package com.qy.hnbt.data.proxy;

import com.qy.hnbt.data.code.InstanceEnum;

//public class ProduceTest implements InvocationHandler {
public class ProduceTest  {
//    private ProduceService produceService;
//    private Object target = null;
//
//
//    public void setProduceService(ProduceService produceService){
//        this.produceService = produceService;
//    }
//
//    @Test
//    public void printProduce() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class clazz = Class.forName("com.qy.hnbt.data.proxy.ProduceTest");
//        setProduceService((ProduceService) clazz.newInstance());
//        produceService.produce("牙膏");
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("代理前");
//        method.invoke(target, args);
//        System.out.println("代理后");
//        return null;
//    }
    public static void main(String[] args) {
        InstanceEnum t1 = InstanceEnum.INSTANCE;
        InstanceEnum t2 = InstanceEnum.INSTANCE;
        System.out.println(t1 == t2);
        int foo = 100;
        for (int i = 0; i < foo; i++) {
            
        }
    }


}
