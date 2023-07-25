package org.geekbang.projects.cs.constants;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:08 AM
 * @description: 外包系统System Code枚举
 */
public enum OutsourcingSystemCode {
    UNKNOWN("unknown", "未知系统"),
    BEI_JING("beijing", "北京外包客服系统"),
    SHANG_HAI("shanghai", "上海外包客服系统"),
    HANG_ZHOU("hangzhou", "杭州外包客服系统"),
    ;
    
    private String code;
    
    private String desc;
    
    OutsourcingSystemCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public static OutsourcingSystemCode ofCode(String code) {
        return Arrays.stream(OutsourcingSystemCode.values()).filter(x -> x.code.equalsIgnoreCase(code)).findFirst()
                .orElse(UNKNOWN);
    }
}
