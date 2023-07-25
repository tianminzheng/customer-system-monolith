package william.cs.theory.aop.benchmark.service;

/**
 * @author ZhangShenao
 * @date 2023/5/29 9:54 AM
 * Description Account接口
 */
public interface AccountService {
    /**
     * 转账
     *
     * @param source 源账户
     * @param dest   目标账户
     * @param amount 转账金额
     */
    void transfer(String source, String dest, long amount);
}
