package com.echain.web.cmcc.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 充值状态
 */
public enum OrderPayResultStatusEnum {

    SUCCESS(1,"充值成功,支付成功,已到账"),
    RECHARGE(2,"充值中,支付成功,未到账"),
    UNKNOW(3,"没取到结果"),
    ;

    private int code;
    private String name;

    private static Map<String, OrderPayResultStatusEnum> tmpMap = new HashMap<String, OrderPayResultStatusEnum>();

    static {
        for (OrderPayResultStatusEnum e : OrderPayResultStatusEnum.values()) {
            tmpMap.put(String.valueOf(e.getCode()), e);
        }
    }

    OrderPayResultStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

	public static OrderPayResultStatusEnum get(Integer code) {
        if(code == null){
            return null;
        }
        return tmpMap.get(String.valueOf(code));
    }

    public static String getName(Integer code) {
        OrderPayResultStatusEnum e = get(code);
        if (e != null) {
            return e.getName();
        }
        return null;
    }

}
