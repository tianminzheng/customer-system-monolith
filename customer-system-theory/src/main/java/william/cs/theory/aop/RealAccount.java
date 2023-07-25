package william.cs.theory.aop;

/**
 * @author ZhangShenao
 * @date 2023/5/26 2:20 PM
 * Description 原始被代理类
 */
public class RealAccount implements Account {
    private String name;

    public RealAccount() {
    }

    public RealAccount(String name) {
        this.name = name;
    }

    @Override
    public void open() {
        System.out.println("open account for " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
