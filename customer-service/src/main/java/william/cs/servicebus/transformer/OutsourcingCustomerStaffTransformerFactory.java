package william.cs.servicebus.transformer;

import william.cs.constants.OutsourcingSystemCode;
import william.cs.servicebus.transformer.hangzhou.HangzhouOutsourcingCustomerStaffTransformer;

/**
 * @author ZhangShenao
 * @date 2023/7/25 11:26 AM
 * @description: 外包客服人员转换器工厂 工厂方法模式
 */
public class OutsourcingCustomerStaffTransformerFactory {
    
    /**
     * 创建外包客服人员转换器
     *
     * @param code 外包客服系统System Code
     * @return 转换器实例
     */
    public static OutsourcingCustomerStaffTransformer createTransformer(OutsourcingSystemCode code) {
        if (OutsourcingSystemCode.HANG_ZHOU == code) {
            return new HangzhouOutsourcingCustomerStaffTransformer();
        }
        
        //TODO 实现其他外包客服系统
        return null;
    }
}
